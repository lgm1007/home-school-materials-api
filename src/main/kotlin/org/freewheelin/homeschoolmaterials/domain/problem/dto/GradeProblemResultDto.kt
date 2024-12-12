package org.freewheelin.homeschoolmaterials.domain.problem.dto

data class GradeProblemResultDto(
	val homeSchoolId: Long,
	val studentId: Long,
	val gradeResults: List<GradeProblemResultItemDto>
) {
	companion object {
		fun of(homeSchoolId: Long, studentId: Long, gradeResults: List<GradeProblemResultItemDto>): GradeProblemResultDto {
			return GradeProblemResultDto(
				homeSchoolId,
				studentId,
				gradeResults
			)
		}
	}
}