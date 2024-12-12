package org.freewheelin.homeschoolmaterials.api.problem.response

import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemResultItemDto

data class GradeProblemResponse(
	val problemId: Long,
	val answer: String,
	val submitAnswer: String,
	val isAnswered: Boolean
) {
	companion object {
		fun from(dto: GradeProblemResultItemDto): GradeProblemResponse {
			return GradeProblemResponse(
				dto.problemId,
				dto.answer,
				dto.submitAnswer,
				dto.isAnswered
			)
		}

		fun listFrom(dtos: List<GradeProblemResultItemDto>): List<GradeProblemResponse> {
			return dtos.map { from(it) }
		}
	}
}