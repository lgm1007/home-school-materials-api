package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.HomeSchool

interface HomeSchoolRepository {
    fun getById(homeSchoolId: Long): HomeSchool

    fun save(homeSchoolDto: HomeSchoolDto): HomeSchool

    fun saveAll(homeSchoolDtos: List<HomeSchoolDto>): List<HomeSchool>

    fun deleteAll()
}