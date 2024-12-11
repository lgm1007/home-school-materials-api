package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.SubmittedProblem

data class SubmittedProblemDto(
    val id: Long,
    val givenHomeSchoolId: Long,
    val problemId: Long,
    val submitAnswer: String,
    val isAnswered: Boolean
) {
    companion object {
        fun from(submittedProlem: SubmittedProblem): SubmittedProblemDto {
            return SubmittedProblemDto(
                submittedProlem.id,
                submittedProlem.givenHomeSchoolId,
                submittedProlem.problemId,
                submittedProlem.submitAnswer,
                submittedProlem.isAnswered
            )
        }

        fun listFrom(submittedProblems: List<SubmittedProblem>): List<SubmittedProblemDto> {
            return submittedProblems.map { from(it) }
        }

        fun of(givenHomeSchoolId: Long, problemId: Long): SubmittedProblemDto {
            return SubmittedProblemDto(
                0,
                givenHomeSchoolId,
                problemId,
                "",
                false
            )
        }
    }
}