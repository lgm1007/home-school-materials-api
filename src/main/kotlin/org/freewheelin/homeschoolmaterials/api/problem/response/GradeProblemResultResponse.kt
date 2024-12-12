package org.freewheelin.homeschoolmaterials.api.problem.response

import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemResultDto

data class GradeProblemResultResponse(
	val homeSchoolId: Long,
	val studentId: Long,
	val gradeResults: List<GradeProblemResponse>
) {
	companion object {
		fun from(dto: GradeProblemResultDto): GradeProblemResultResponse {
			return GradeProblemResultResponse(
				dto.homeSchoolId,
				dto.studentId,
				GradeProblemResponse.listFrom(dto.gradeResults)
			)
		}
	}
}