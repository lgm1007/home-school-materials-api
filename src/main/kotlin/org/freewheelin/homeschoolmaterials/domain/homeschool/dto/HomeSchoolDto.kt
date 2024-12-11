package org.freewheelin.homeschoolmaterials.domain.homeschool.dto

import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.HomeSchool
import java.time.LocalDateTime

data class HomeSchoolDto(
    val id: Long,
    val teacherId: Long,
    val name: String,
    val createdDate: LocalDateTime
) {
    companion object {
        fun from(homeSchool: HomeSchool): HomeSchoolDto {
            return HomeSchoolDto(
                homeSchool.id,
                homeSchool.teacherId,
                homeSchool.name,
                homeSchool.createdDate
            )
        }

        fun listFrom(homeSchools: List<HomeSchool>): List<HomeSchoolDto> {
            return homeSchools.map { from(it) }
        }

        fun of(teacherId: Long, name: String): HomeSchoolDto {
            return HomeSchoolDto(
                0,
                teacherId,
                name,
                LocalDateTime.now()
            )
        }
    }
}