package org.freewheelin.homeschoolmaterials.api.problem.request

data class GradeSubmitProblemItemRequest(
	val problemId: Long,
	val submitAnswer: String
) {
}