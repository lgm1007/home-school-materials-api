package org.freewheelin.homeschoolmaterials.domain.problem.dto

data class CreateSubmittedProblemDto(
    val homeSchoolId: Long,
    val givenHomeSchoolId: Long
) {
    companion object {
        fun of(homeSchoolId: Long, givenHomeSchoolId: Long): CreateSubmittedProblemDto {
            return CreateSubmittedProblemDto(homeSchoolId, givenHomeSchoolId)
        }
    }
}