package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val MAX_PROBLEM_LENGTH = 50

@Service
class HomeSchoolService(
    private val homeSchoolRepository: HomeSchoolRepository,
    private val homeSchoolProblemRepository: HomeSchoolProblemRepository,
    private val givenHomeSchoolRepository: GivenHomeSchoolRepository
) {
    @Transactional(readOnly = true)
    fun getHomeSchoolById(homeSchoolId: Long): HomeSchoolDto {
        return HomeSchoolDto.from(
            homeSchoolRepository.getById(homeSchoolId)
        )
    }

    @Transactional
    fun createHomeSchool(createDto: CreateHomeSchoolDto): Long {
        createDto.checkMaxProblemLength(MAX_PROBLEM_LENGTH)

        val homeSchoolDto = HomeSchoolDto.of(createDto.teacherId, createDto.name)

        val homeSchoolId = homeSchoolRepository.save(homeSchoolDto).id

        val homeSchoolProblemDtos = createDto.problemIds.map {
            HomeSchoolProblemDto.of(homeSchoolId, it)
        }

        homeSchoolProblemRepository.saveAll(homeSchoolProblemDtos)

        return homeSchoolId
    }

    fun createGivenHomeSchoolByStudent(presentDto: PresentHomeSchoolDto): List<GivenHomeSchoolDto> {
        val givenHomeSchoolDtos = presentDto.studentIds
            .filter { !givenHomeSchoolRepository.isExistByHomeSchoolIdAndStudentId(presentDto.homeSchoolId, it) }
            .map { GivenHomeSchoolDto.of(presentDto.homeSchoolId, it) }

        return GivenHomeSchoolDto.listFrom(givenHomeSchoolRepository.saveAll(givenHomeSchoolDtos))
    }

    fun getAllGivenHomeSchoolByHomeSchoolIdIsDone(homeSchoolId: Long, isDone: Boolean): List<GivenHomeSchoolDto> {
        return GivenHomeSchoolDto.listFrom(
            givenHomeSchoolRepository.getAllByHomeSchoolIdAndIsDone(homeSchoolId, isDone)
        )
    }

    fun updateGivenHomeSchoolDoneByHomeSchoolIdAndStudentId(homeSchoolId: Long, studentId: Long): GivenHomeSchoolDto {
        val givenHomeSchool =
            givenHomeSchoolRepository.getByHomeSchoolIdAndStudentId(homeSchoolId, studentId)

        givenHomeSchool.setIsDoneTrue()

        return GivenHomeSchoolDto.from(givenHomeSchool)
    }
}