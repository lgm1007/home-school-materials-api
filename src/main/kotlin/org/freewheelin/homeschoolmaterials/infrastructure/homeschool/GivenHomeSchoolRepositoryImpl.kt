package org.freewheelin.homeschoolmaterials.infrastructure.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.GivenHomeSchoolRepository
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.GivenHomeSchool
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.webjars.NotFoundException

@Repository
class GivenHomeSchoolRepositoryImpl(
    private val jpaRepository: GivenHomeSchoolJpaRepository
) : GivenHomeSchoolRepository {
    override fun getById(id: Long): GivenHomeSchool {
        return jpaRepository.findByIdOrNull(id)
            ?: throw NotFoundException("ID: ${id}에 해당하는 GivenHomeSchool을 찾을 수 없음")
    }

    override fun getByHomeSchoolIdAndStudentId(homeSchoolId: Long, studentId: Long): GivenHomeSchool {
        return jpaRepository.findByHomeSchoolIdAndStudentId(homeSchoolId, studentId)
            ?: throw NotFoundException("HomeSchoolId: ${homeSchoolId}, StudentId: ${studentId}에 해당하는 GivenHomeSchool을 찾을 수 없음")
    }

    override fun save(givenHomeSchoolDto: GivenHomeSchoolDto): GivenHomeSchool {
        return jpaRepository.save(GivenHomeSchool.from(givenHomeSchoolDto))
    }

    override fun saveAll(givenHomeSchoolDtos: List<GivenHomeSchoolDto>): List<GivenHomeSchool> {
        return jpaRepository.saveAll(GivenHomeSchool.listFrom(givenHomeSchoolDtos))
    }

    override fun deleteAll() {
        jpaRepository.deleteAll()
    }
}