package org.freewheelin.homeschoolmaterials.api.homeschool.response

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto

data class PresentHomeSchoolResponse(
	val homeSchoolId: Long,
	val studentIds: List<Long>,
) {
	companion object {
		fun from(dto: PresentHomeSchoolDto): PresentHomeSchoolResponse {
			return PresentHomeSchoolResponse(
				dto.homeSchoolId,
				dto.studentIds
			)
		}
	}
}