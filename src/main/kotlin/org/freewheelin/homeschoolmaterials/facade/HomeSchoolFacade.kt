package org.freewheelin.homeschoolmaterials.facade

import org.freewheelin.homeschoolmaterials.domain.homeschool.HomeSchoolService
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemService
import org.freewheelin.homeschoolmaterials.domain.problem.dto.CreateSubmittedProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemResultDto
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class HomeSchoolFacade(
    private val homeSchoolService: HomeSchoolService,
    private val problemService: ProblemService
) {
    @Transactional
    fun presentHomeSchool(presentHomeSchoolDto: PresentHomeSchoolDto): PresentHomeSchoolDto {
        val givenHomeSchoolDtos = homeSchoolService.createGivenHomeSchoolByStudent(presentHomeSchoolDto)
        givenHomeSchoolDtos.forEach {
            problemService.createSubmittedProblems(
                CreateSubmittedProblemDto.of(it.homeSchoolId, it.id)
            )
        }

        return presentHomeSchoolDto
    }

    @Transactional
    fun gradeProblems(gradeProblemDto: GradeProblemDto): GradeProblemResultDto {
        val givenHomeSchoolDto =
            homeSchoolService.updateGivenHomeSchoolDoneByHomeSchoolIdAndStudentId(
                gradeProblemDto.homeSchoolId, gradeProblemDto.studentId
            )
        val gradeProblemResultItemDtos =
            problemService.gradeProblems(givenHomeSchoolDto.id, gradeProblemDto.submitProblems)

        return GradeProblemResultDto.of(
            givenHomeSchoolDto.studentId,
            givenHomeSchoolDto.homeSchoolId,
            gradeProblemResultItemDtos
        )
    }
}