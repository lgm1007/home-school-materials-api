package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

import org.freewheelin.homeschoolmaterials.api.homeschool.request.CreateHomeSchoolRequest

data class CreateHomeSchoolDto(
    val name: String,
    val teacherId: Long,
    val problemIds: List<Long>
) {
    companion object {
        fun from(createRequest: CreateHomeSchoolRequest): CreateHomeSchoolDto {
            return CreateHomeSchoolDto(
                createRequest.name,
                createRequest.teacherId,
                createRequest.problemIds
            )
        }
    }
}