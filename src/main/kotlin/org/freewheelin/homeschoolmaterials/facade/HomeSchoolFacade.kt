package org.freewheelin.homeschoolmaterials.facade

import org.freewheelin.homeschoolmaterials.domain.homeschool.HomeSchoolService
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemService
import org.freewheelin.homeschoolmaterials.domain.problem.dto.CreateSubmittedProblemDto
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class HomeSchoolFacade(
    private val homeSchoolService: HomeSchoolService,
    private val problemService: ProblemService
) {
    @Transactional
    fun presentHomeSchool(presentHomeSchoolDto: PresentHomeSchoolDto): PresentHomeSchoolDto {
        val givenHomeSchoolDto = homeSchoolService.createGivenHomeSchoolByStudent(presentHomeSchoolDto)
        problemService.createSubmittedProblems(
            CreateSubmittedProblemDto.of(givenHomeSchoolDto.homeSchoolId, givenHomeSchoolDto.id)
        )

        return presentHomeSchoolDto
    }
}