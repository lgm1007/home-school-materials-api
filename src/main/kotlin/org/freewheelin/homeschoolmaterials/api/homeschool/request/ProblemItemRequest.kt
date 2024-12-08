package org.freewheelin.homeschoolmaterials.api.homeschool.request

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType

data class ProblemItemRequest(
	val id: Long,
	val unitCode: String,
	val problemType: ProblemType,
	val level: Int,
	val question: String,
	val answer: String
) {
}