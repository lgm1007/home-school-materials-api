package org.freewheelin.homeschoolmaterials.api.problem.response

data class GradeProblemResultResponse(
	val studentId: Long,
	val homeSchoolId: Long,
	val gradeResults: List<GradeProblemResponse>
) {
}