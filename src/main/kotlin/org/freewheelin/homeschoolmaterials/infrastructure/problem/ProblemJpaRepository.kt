package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProblemJpaRepository : JpaRepository<Problem, Long> {
    @Query("SELECT p FROM Problem p WHERE p.unitCode IN (:unitCodes) AND p.problemType = :problemType")
    fun findAllByUnitCodeInAndProblemType(
        @Param("unitCodes") unitCodes: List<UnitCode>,
        @Param("problemType") problemType: ProblemType
    ): List<Problem>
}