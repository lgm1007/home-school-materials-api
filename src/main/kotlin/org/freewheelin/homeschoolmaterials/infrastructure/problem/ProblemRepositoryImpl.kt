package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.webjars.NotFoundException

@Repository
class ProblemRepositoryImpl(
    private val jpaRepository: ProblemJpaRepository
) : ProblemRepository {
    override fun getAllByUnitCodesAndProblemType(unitCodes: List<UnitCode>, problemType: ProblemType): List<Problem> {
        return jpaRepository.findAllByUnitCodeInAndProblemType(unitCodes, problemType)
    }

    override fun getById(id: Long): Problem {
        return jpaRepository.findByIdOrNull(id)
            ?: throw NotFoundException("ID: ${id}인 Problem을 찾을 수 없음")
    }

    override fun getAllByIds(ids: List<Long>): List<Problem> {
        return jpaRepository.findAllByIdIn(ids)
    }

    override fun saveAll(problemDtos: List<ProblemDto>): List<Problem> {
        return jpaRepository.saveAll(Problem.listFrom(problemDtos))
    }

    override fun deleteAll() {
        return jpaRepository.deleteAll()
    }
}