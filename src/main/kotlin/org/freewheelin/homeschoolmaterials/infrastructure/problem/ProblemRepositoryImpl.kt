package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.springframework.stereotype.Repository

@Repository
class ProblemRepositoryImpl(
    private val jpaRepository: ProblemJpaRepository
) : ProblemRepository {
    override fun getAllByUnitCodesAndProblemType(unitCodes: List<UnitCode>, problemType: ProblemType): List<Problem> {
        return jpaRepository.findAllByUnitCodeInAndProblemType(unitCodes, problemType)
    }

    override fun saveAll(problemDtos: List<ProblemDto>): List<Problem> {
        return jpaRepository.saveAll(Problem.listFrom(problemDtos))
    }

    override fun deleteAll() {
        return jpaRepository.deleteAll()
    }
}