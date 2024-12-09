package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.springframework.stereotype.Service

@Service
class ProblemService(
    private val problemRepository: ProblemRepository,
    private val homeSchoolProblemRepository: HomeSchoolProblemRepository
) {
    fun getProblems(param: FindProblemParam): List<ProblemDto> {
        val problems = problemRepository.getAllByUnitCodesAndProblemType(param.unitCodes, param.problemType)
        val realProblemCount = Math.min(param.totalCount, problems.size)
        val problemLevelCountMap = param.problemLevel.allocateProblemCount(realProblemCount)    // 난이도 별 문제 개수가 담긴 Map

        val lowLevelProblems = problems.filter {
            ProblemLevel.LOW.levels.contains(it.level)
        }.take(problemLevelCountMap[ProblemLevel.LOW]!!)

        val mediumProblems = problems.filter {
            ProblemLevel.MEDIUM.levels.contains(it.level)
        }.take(problemLevelCountMap[ProblemLevel.MEDIUM]!!)

        val highProblems = problems.filter {
            ProblemLevel.HIGH.levels.contains(it.level)
        }.take(problemLevelCountMap[ProblemLevel.HIGH]!!)

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
}