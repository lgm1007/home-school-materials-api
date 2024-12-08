package org.freewheelin.homeschoolmaterials.api.problem.response

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType

data class ProblemResponse(
	val id: Long,
	val unitCode: String,
	val problemType: ProblemType,
	val level: Int,
) {
	lateinit var question: String
		private set

	lateinit var answer: String
		private set

	constructor(id: Long, unitCode: String, problemType: ProblemType, level: Int, question: String) : this(id, unitCode, problemType, level) {
		this.question = question
	}

	constructor(id: Long, unitCode: String, problemType: ProblemType, level: Int, question: String, answer: String) : this(id, unitCode, problemType, level) {
		this.question = question
		this.answer = answer
	}
}