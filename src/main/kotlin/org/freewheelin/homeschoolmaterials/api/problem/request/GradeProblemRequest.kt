package org.freewheelin.homeschoolmaterials.api.problem.request

data class GradeProblemRequest(
	val studentId: Long,
	val homeSchoolId: Long,
	val submitProblems: List<GradeSubmitProblemItemRequest>
) {
}