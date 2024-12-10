package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.springframework.stereotype.Service

@Service
class ProblemService(
    private val problemRepository: ProblemRepository
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
}