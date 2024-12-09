package org.freewheelin.homeschoolmaterials.domain.problem

enum class ProblemLevel(val levels: List<Int>) {
	LOW(listOf(1)),
	MEDIUM(listOf(2, 3, 4)),
	HIGH(listOf(5)),
}