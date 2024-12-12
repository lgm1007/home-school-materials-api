package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

import org.freewheelin.homeschoolmaterials.api.homeschool.request.CreateHomeSchoolRequest

data class CreateHomeSchoolDto(
    val name: String,
    val teacherId: Long,
    val problemIds: List<Long>
) {
    fun checkMaxProblemLength(maxLength: Int) {
        if (problemIds.size > maxLength) {
            throw IllegalArgumentException("학습지의 문제 수는 ${maxLength}개를 넘길 수 없습니다.")
        }
    }

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