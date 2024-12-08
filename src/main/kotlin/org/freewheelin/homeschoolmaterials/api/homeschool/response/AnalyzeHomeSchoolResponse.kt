package org.freewheelin.homeschoolmaterials.api.homeschool.response

data class AnalyzeHomeSchoolResponse(
	val homeSchoolId: Long,
	val name: String,
	val studentDataList: List<AnalyzeHomeSchoolStudentResponse>,
	val problemDataList: List<AnalyzeHomeSchoolProblemResponse>,
) {
}