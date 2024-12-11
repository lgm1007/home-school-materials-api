package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

data class AnalyzeHomeSchoolResultDto(
	val homeSchoolId: Long,
	val name: String,
	val studentDataList: List<AnalyzeHomeSchoolStudentResultDto>,
	val problemDataList: List<AnalyzeHomeSchoolProblemResultDto>
) {
	companion object {
		fun of(
			homeSchoolId: Long,
			name: String,
			studentDataList: List<AnalyzeHomeSchoolStudentResultDto>,
			problemDataList: List<AnalyzeHomeSchoolProblemResultDto>
		): AnalyzeHomeSchoolResultDto {
			return AnalyzeHomeSchoolResultDto(
				homeSchoolId,
				name,
				studentDataList,
				problemDataList
			)
		}
	}
}