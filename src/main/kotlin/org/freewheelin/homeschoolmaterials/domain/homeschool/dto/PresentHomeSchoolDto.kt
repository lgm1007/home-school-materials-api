package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

import org.freewheelin.homeschoolmaterials.api.homeschool.request.PresentHomeSchoolRequest

data class PresentHomeSchoolDto(
    val homeSchoolId: Long,
    val studentIds: List<Long>
) {
    companion object {
        fun from(request: PresentHomeSchoolRequest): PresentHomeSchoolDto {
            return PresentHomeSchoolDto(
                request.homeSchoolId,
                request.studentIds
            )
        }
    }
}