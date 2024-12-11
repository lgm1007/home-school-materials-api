package org.freewheelin.homeschoolmaterials.api.problem.request

data class GradeProblemRequest(
	val homeSchoolId: Long,
	val studentId: Long,
	val submitProblems: List<GradeSubmitProblemItemRequest>
) {
}