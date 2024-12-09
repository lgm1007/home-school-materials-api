package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem

data class ProblemDto(
    val id: Long,
    val unitCode: UnitCode,
    val problemType: ProblemType,
    val level: Int,
    val question: String,
    val answer: String,
) {
    companion object {
        fun from(problem: Problem): ProblemDto {
            return ProblemDto(
                problem.id,
                problem.unitCode,
                problem.problemType,
                problem.level,
                problem.question,
                problem.answer
            )
        }

        fun listFrom(problems: List<Problem>): List<ProblemDto> {
            return problems.map { from(it) }
        }
    }
}