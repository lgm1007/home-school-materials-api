package org.freewheelin.homeschoolmaterials.api.homeschool.response

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.AnalyzeHomeSchoolStudentResultDto

data class AnalyzeHomeSchoolStudentResponse(
	val studentId: Long,
	val answerRate: Double,
) {
	companion object {
		fun from(resultDto: AnalyzeHomeSchoolStudentResultDto): AnalyzeHomeSchoolStudentResponse {
			return AnalyzeHomeSchoolStudentResponse(
				resultDto.studentId,
				resultDto.answerRate,
			)
		}

		fun listFrom(resultDtos: List<AnalyzeHomeSchoolStudentResultDto>): List<AnalyzeHomeSchoolStudentResponse> {
			return resultDtos.map { from(it) }
		}
	}
}