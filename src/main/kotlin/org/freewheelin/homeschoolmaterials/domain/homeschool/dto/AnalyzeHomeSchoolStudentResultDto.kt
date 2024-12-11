package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

data class AnalyzeHomeSchoolStudentResultDto(
	val studentId: Long,
	val answerRate: Double,
) {
	companion object {
		fun of(studentId: Long, answerRate: Double): AnalyzeHomeSchoolStudentResultDto {
			return AnalyzeHomeSchoolStudentResultDto(studentId, answerRate)
		}
	}
}