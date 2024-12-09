package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemLevel
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode

data class FindProblemParam(
    val totalCount: Int,
    val unitCodes: List<UnitCode>,
    val problemType: ProblemType,
    val problemLevel: ProblemLevel
) {
}