package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

data class CreateHomeSchoolDto(
    val name: String,
    val teacherId: Long,
    val problemIds: List<Long>
) {
}