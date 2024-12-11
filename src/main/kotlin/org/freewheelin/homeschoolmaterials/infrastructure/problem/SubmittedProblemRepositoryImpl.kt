package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.domain.problem.SubmittedProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.SubmittedProblem
import org.springframework.stereotype.Repository

@Repository
class SubmittedProblemRepositoryImpl(
    private val jpaRepository: SubmittedProblemJpaRepository
) : SubmittedProblemRepository {
    override fun getAllByGivenHomeSchoolId(givenHomeSchoolId: Long): List<SubmittedProblem> {
        return jpaRepository.findAllByGivenHomeSchoolId(givenHomeSchoolId)
    }

    override fun save(submittedProblemDto: SubmittedProblemDto): SubmittedProblem {
        return jpaRepository.save(SubmittedProblem.from(submittedProblemDto))
    }

    override fun saveAll(submittedProblemDtos: List<SubmittedProblemDto>): List<SubmittedProblem> {
        return jpaRepository.saveAll(SubmittedProblem.listFrom(submittedProblemDtos))
    }

    override fun deleteAll() {
        jpaRepository.deleteAll()
    }
}