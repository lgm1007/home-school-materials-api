package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.api.problem.request.GradeSubmitProblemItemRequest

data class GradeSubmitProblemItemDto(
	val problemId: Long,
	val submitAnswer: String,
) {
	companion object {
		fun from(request: GradeSubmitProblemItemRequest): GradeSubmitProblemItemDto {
			return GradeSubmitProblemItemDto(
				request.problemId,
				request.submitAnswer,
			)
		}

		fun listFrom(requests: List<GradeSubmitProblemItemRequest>): List<GradeSubmitProblemItemDto> {
			return requests.map { from(it) }
		}
	}
}