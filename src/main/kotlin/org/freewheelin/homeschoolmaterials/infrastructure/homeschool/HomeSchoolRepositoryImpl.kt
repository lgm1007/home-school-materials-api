package org.freewheelin.homeschoolmaterials.infrastructure.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.HomeSchoolRepository
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.HomeSchool
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.webjars.NotFoundException

@Repository
class HomeSchoolRepositoryImpl(
    private val jpaRepository: HomeSchoolJpaRepository
) : HomeSchoolRepository {
    override fun getById(homeSchoolId: Long): HomeSchool {
        return jpaRepository.findByIdOrNull(homeSchoolId)
            ?: throw NotFoundException("ID: ${homeSchoolId}에 해당하는 HomeSchool을 찾을 수 없음");
    }

    override fun save(homeSchoolDto: HomeSchoolDto): HomeSchool {
        return jpaRepository.save(HomeSchool.from(homeSchoolDto))
    }

    override fun saveAll(homeSchoolDtos: List<HomeSchoolDto>): List<HomeSchool> {
        return jpaRepository.saveAll(HomeSchool.listFrom(homeSchoolDtos))
    }

    override fun deleteAll() {
        jpaRepository.deleteAll()
    }
}