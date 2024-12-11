package org.freewheelin.homeschoolmaterials.domain.problem.dto

data class GradeProblemDto(
	val studentId: Long,
	val homeSchoolId: Long,
	val submitProblems: List<GradeSubmitProblemItemDto>
) {
}