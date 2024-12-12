package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val MAX_PROBLEM_LENGTH = 50

@Service
class HomeSchoolService(
    private val homeSchoolRepository: HomeSchoolRepository,
    private val homeSchoolProblemRepository: HomeSchoolProblemRepository
) {
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
}