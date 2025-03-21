package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.AnalyzeHomeSchoolProblemResultDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.AnalyzeHomeSchoolStudentResultDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.*
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.SubmittedProblem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.webjars.NotFoundException

@Service
class ProblemService(
    private val problemRepository: ProblemRepository,
    private val homeSchoolProblemRepository: HomeSchoolProblemRepository,
    private val submittedProblemRepository: SubmittedProblemRepository,
    private val answerRateAnalyzer: AnswerRateAnalyzer
) {
    fun getProblems(param: FindProblemParam): List<ProblemDto> {
        val problems = problemRepository.getAllByUnitCodesAndProblemType(param.unitCodes, param.problemType)
        val realProblemCount = Math.min(param.totalCount, problems.size)
        val problemLevelCountMap = param.problemLevel.allocateProblemCount(realProblemCount)    // 난이도 별 문제 개수가 담긴 Map

        val lowLevelProblems = mutableListOf<Problem>()
        val mediumProblems = mutableListOf<Problem>()
        val highProblems = mutableListOf<Problem>()

        problems.forEach {
            when {
                ProblemLevel.LOW.levels.contains(it.level) && lowLevelProblems.size < problemLevelCountMap[ProblemLevel.LOW]!! -> lowLevelProblems.add(it)
                ProblemLevel.MEDIUM.levels.contains(it.level) && mediumProblems.size < problemLevelCountMap[ProblemLevel.MEDIUM]!! -> mediumProblems.add(it)
                ProblemLevel.HIGH.levels.contains(it.level) && highProblems.size < problemLevelCountMap[ProblemLevel.HIGH]!! -> highProblems.add(it)
            }
        }

        val resultProblems = lowLevelProblems + mediumProblems + highProblems

        return ProblemDto.listFrom(resultProblems)
    }

    fun getProblemByHomeSchoolId(homeSchoolId: Long): List<ProblemDto> {
        val problemIds = homeSchoolProblemRepository.getAllByHomeSchoolId(homeSchoolId).map {
            it.problemId
        }
        val problems = problemRepository.getAllByIds(problemIds)

        return ProblemDto.listFrom(problems)
    }

    fun createSubmittedProblems(createSubmittedDto: CreateSubmittedProblemDto) {
        val submittedProblemDtos = homeSchoolProblemRepository.getAllByHomeSchoolId(createSubmittedDto.homeSchoolId).map {
            val problemId = it.problemId
            SubmittedProblemDto.of(createSubmittedDto.givenHomeSchoolId, problemId)
        }

        submittedProblemRepository.saveAll(submittedProblemDtos)
    }

    @Transactional(readOnly = true)
    fun analyzeStudentAnswerRateData(givenHomeSchoolDtos: List<GivenHomeSchoolDto>): List<AnalyzeHomeSchoolStudentResultDto> {
        return givenHomeSchoolDtos.map {
            val submittedProblemDtos = SubmittedProblemDto.listFrom(
                submittedProblemRepository.getAllByGivenHomeSchoolId(it.id)
            )
            val answerRate = answerRateAnalyzer.calculateStudentAnswerRate(submittedProblemDtos)

            AnalyzeHomeSchoolStudentResultDto.of(it.studentId, answerRate)
        }
    }

    @Transactional(readOnly = true)
    fun analyzeProblemAnswerRateData(givenHomeSchoolDtos: List<GivenHomeSchoolDto>): List<AnalyzeHomeSchoolProblemResultDto> {
        val givenHomeSchoolIds = givenHomeSchoolDtos.map { it.id }
        val submittedProblemDtos = SubmittedProblemDto.listFrom(
            submittedProblemRepository.getAllByGivenHomeSchoolIdsIn(givenHomeSchoolIds)
        )

        return submittedProblemDtos
            .distinctBy { it.problemId }
            .map {
                val answerRate =
                    answerRateAnalyzer.calculateProblemAnswerRate(it.problemId, submittedProblemDtos)

                AnalyzeHomeSchoolProblemResultDto.of(it.problemId, answerRate)
            }
    }

    fun gradeProblems(
        givenHomeSchoolId: Long,
        submitProblems: List<GradeSubmitProblemItemDto>
    ): List<GradeProblemResultItemDto> {
        val savedSubmittedProblems = submittedProblemRepository.getAllByGivenHomeSchoolId(givenHomeSchoolId)

        return submitProblems.map { submitProblem ->
            val answer = problemRepository.getById(submitProblem.problemId).answer
            val findSubmittedProblem = savedSubmittedProblems.find { it.problemId == submitProblem.problemId }
                ?: throw NotFoundException("ID: ${submitProblem.problemId}인 문제 요소가 존재하지 않음")

            findSubmittedProblem.gradingProblem(submitProblem.submitAnswer, answer)

            GradeProblemResultItemDto.of(
                submitProblem.problemId, answer, submitProblem.submitAnswer, findSubmittedProblem.isAnswered
            )
        }
    }
}