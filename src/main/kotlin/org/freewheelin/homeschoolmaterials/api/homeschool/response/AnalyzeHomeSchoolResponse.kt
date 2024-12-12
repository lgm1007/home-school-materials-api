package org.freewheelin.homeschoolmaterials.api.homeschool.response

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.AnalyzeHomeSchoolResultDto

data class AnalyzeHomeSchoolResponse(
	val homeSchoolId: Long,
	val name: String,
	val studentDataList: List<AnalyzeHomeSchoolStudentResponse>,
	val problemDataList: List<AnalyzeHomeSchoolProblemResponse>,
) {
	companion object {
		fun from(resultDto: AnalyzeHomeSchoolResultDto): AnalyzeHomeSchoolResponse {
			return AnalyzeHomeSchoolResponse(
				resultDto.homeSchoolId,
				resultDto.name,
				AnalyzeHomeSchoolStudentResponse.listFrom(resultDto.studentDataList),
				AnalyzeHomeSchoolProblemResponse.listFrom(resultDto.problemDataList)
			)
		}
	}
}