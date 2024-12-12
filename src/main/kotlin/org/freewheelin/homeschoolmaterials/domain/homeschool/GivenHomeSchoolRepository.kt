package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.GivenHomeSchool

interface GivenHomeSchoolRepository {
    fun getById(id: Long): GivenHomeSchool

    fun save(givenHomeSchoolDto: GivenHomeSchoolDto): GivenHomeSchool

    fun saveAll(givenHomeSchoolDtos: List<GivenHomeSchoolDto>): List<GivenHomeSchool>

    fun deleteAll()
}