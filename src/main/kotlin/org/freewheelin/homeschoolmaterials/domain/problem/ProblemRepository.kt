package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem

interface ProblemRepository {
    fun getAllByUnitCodesAndProblemType(unitCodes: List<UnitCode>, problemType: ProblemType): List<Problem>

    fun getById(id: Long): Problem

    fun getAllByIds(ids: List<Long>): List<Problem>

    fun saveAll(problemDtos: List<ProblemDto>): List<Problem>

    fun deleteAll()
}