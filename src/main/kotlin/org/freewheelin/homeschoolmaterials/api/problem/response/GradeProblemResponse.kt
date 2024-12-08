package org.freewheelin.homeschoolmaterials.api.problem.response

data class GradeProblemResponse(
	val problemId: Long,
	val answer: String,
	val submitAnswer: String,
	val isAnswered: Boolean
) {
}