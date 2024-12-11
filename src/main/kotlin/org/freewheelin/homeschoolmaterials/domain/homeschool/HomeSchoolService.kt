package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HomeSchoolService(
    private val homeSchoolRepository: HomeSchoolRepository,
    private val homeSchoolProblemRepository: HomeSchoolProblemRepository,
    private val givenHomeSchoolRepository: GivenHomeSchoolRepository
) {
    @Transactional
    fun createHomeSchool(createDto: CreateHomeSchoolDto): Long {
        val homeSchoolDto = HomeSchoolDto.of(createDto.teacherId, createDto.name)

        val homeSchoolId = homeSchoolRepository.save(homeSchoolDto).id

        val homeSchoolProblemDtos = createDto.problemIds.map {
            HomeSchoolProblemDto.of(homeSchoolId, it)
        }

        homeSchoolProblemRepository.saveAll(homeSchoolProblemDtos)

        return homeSchoolId
    }

    fun createGivenHomeSchoolByStudent(presentDto: PresentHomeSchoolDto): List<GivenHomeSchoolDto> {
        val givenHomeSchoolDtos = presentDto.studentIds.map {
            GivenHomeSchoolDto.of(presentDto.homeSchoolId, it)
        }

        return GivenHomeSchoolDto.listFrom(givenHomeSchoolRepository.saveAll(givenHomeSchoolDtos))
    }

    fun updateGivenHomeSchoolDoneByHomeSchoolIdAndStudentId(homeSchoolId: Long, studentId: Long): GivenHomeSchoolDto {
        val givenHomeSchool =
            givenHomeSchoolRepository.getByHomeSchoolIdAndStudentId(homeSchoolId, studentId)

        givenHomeSchool.setIsDoneTrue()

        return GivenHomeSchoolDto.from(givenHomeSchool)
    }
}