package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

data class AnalyzeHomeSchoolProblemResultDto(
	val problemId: Long,
	val answerRatio: Double,
) {
	companion object {
		fun of(problemId: Long, answerRatio: Double): AnalyzeHomeSchoolProblemResultDto {
			return AnalyzeHomeSchoolProblemResultDto(problemId, answerRatio)
		}
	}
}