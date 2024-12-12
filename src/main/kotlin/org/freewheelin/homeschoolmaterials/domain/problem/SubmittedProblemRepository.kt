package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.SubmittedProblem

interface SubmittedProblemRepository {
    fun getAllByGivenHomeSchoolId(givenHomeSchoolId: Long): List<SubmittedProblem>

    fun getAllByGivenHomeSchoolIdsIn(givenHomeSchoolIds: List<Long>): List<SubmittedProblem>

    fun save(submittedProblemDto: SubmittedProblemDto): SubmittedProblem

    fun saveAll(submittedProblemDtos: List<SubmittedProblemDto>): List<SubmittedProblem>

    fun deleteAll()
}