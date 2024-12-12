package org.freewheelin.homeschoolmaterials.api.homeschool.response

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.AnalyzeHomeSchoolProblemResultDto

data class AnalyzeHomeSchoolProblemResponse(
	val problemId: Long,
	val answerRate: Double,
) {
	companion object {
		fun from(resultDto: AnalyzeHomeSchoolProblemResultDto): AnalyzeHomeSchoolProblemResponse {
			return AnalyzeHomeSchoolProblemResponse(
				resultDto.problemId,
				resultDto.answerRate
			)
		}

		fun listFrom(resultDtos: List<AnalyzeHomeSchoolProblemResultDto>): List<AnalyzeHomeSchoolProblemResponse> {
			return resultDtos.map { from(it) }
		}
	}
}