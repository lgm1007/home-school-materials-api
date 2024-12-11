package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.api.problem.request.GradeProblemRequest

data class GradeProblemDto(
	val homeSchoolId: Long,
	val studentId: Long,
	val submitProblems: List<GradeSubmitProblemItemDto>
) {
	companion object {
		fun from(request: GradeProblemRequest): GradeProblemDto {
			return GradeProblemDto(
				request.homeSchoolId,
				request.studentId,
				GradeSubmitProblemItemDto.listFrom(request.submitProblems)
			)
		}
	}
}