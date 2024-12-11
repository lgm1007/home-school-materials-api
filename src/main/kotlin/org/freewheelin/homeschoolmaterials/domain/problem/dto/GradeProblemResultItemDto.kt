package org.freewheelin.homeschoolmaterials.domain.problem.dto

data class GradeProblemResultItemDto(
	val problemId: Long,
	val answer: String,
	val submitAnswer: String,
	val isAnswered: Boolean
) {
	companion object {
		fun of(
			problemId: Long,
			answer: String,
			submitAnswer: String,
			isAnswered: Boolean
		): GradeProblemResultItemDto {
			return GradeProblemResultItemDto(
				problemId,
				answer,
				submitAnswer,
				isAnswered
			)
		}
	}
}