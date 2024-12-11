package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.GivenHomeSchool

data class GivenHomeSchoolDto(
    val id: Long,
    val homeSchoolId: Long,
    val studentId: Long,
    val isDone: Boolean
) {
    companion object {
        fun from(givenHomeSchool: GivenHomeSchool): GivenHomeSchoolDto {
            return GivenHomeSchoolDto(
                givenHomeSchool.id,
                givenHomeSchool.homeSchoolId,
                givenHomeSchool.studentId,
                givenHomeSchool.isDone
            )
        }

        fun listFrom(givenHomeSchools: List<GivenHomeSchool>): List<GivenHomeSchoolDto> {
            return givenHomeSchools.map { from(it) }
        }

        fun of(homeSchoolId: Long, studentId: Long): GivenHomeSchoolDto {
            return GivenHomeSchoolDto(
                0,
                homeSchoolId,
                studentId,
                false
            )
        }
    }
}