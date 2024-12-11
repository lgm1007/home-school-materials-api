package org.freewheelin.homeschoolmaterials.domain.problem.dto

data class GradeProblemResultDto(
	val studentId: Long,
	val homeSchoolId: Long,
	val gradeResults: List<GradeProblemResultItemDto>
) {
	companion object {
		fun of(studentId: Long, homeSchoolId: Long, gradeResults: List<GradeProblemResultItemDto>): GradeProblemResultDto {
			return GradeProblemResultDto(
				studentId,
				homeSchoolId,
				gradeResults
			)
		}
	}
}