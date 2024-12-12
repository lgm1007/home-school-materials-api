package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.springframework.stereotype.Component

@Component
class AnswerRateAnalyzer {
	fun calculateStudentAnswerRate(submittedProblems: List<SubmittedProblemDto>): Double {
		val totalCount = submittedProblems.size
		val answeredCount = submittedProblems.filter { it.isAnswered }.size

		return ((answeredCount.toDouble() / totalCount.toDouble()) * 100) * 10.0 / 10.0
	}

	fun calculateProblemAnswerRate(problemId: Long, submittedProblems: List<SubmittedProblemDto>): Double {
		val filteredProblems = submittedProblems.filter { it.problemId == problemId }
		val problemCount = filteredProblems.size
		val answeredCount = filteredProblems.filter { it.isAnswered }.size

		return ((answeredCount.toDouble() / problemCount.toDouble()) * 100) * 10.0 / 10.0
	}
}