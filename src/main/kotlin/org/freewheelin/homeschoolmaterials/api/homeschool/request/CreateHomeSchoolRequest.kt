package org.freewheelin.homeschoolmaterials.api.homeschool.request

data class CreateHomeSchoolRequest(
	val name: String,
	val teacherId: Long,
	val problemList: List<ProblemItemRequest>
) {
}