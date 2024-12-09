package org.freewheelin.homeschoolmaterials.api.homeschool.request

data class PresentHomeSchoolRequest(
	val homeSchoolId: Long,
	val studentId: Long,
) {
}