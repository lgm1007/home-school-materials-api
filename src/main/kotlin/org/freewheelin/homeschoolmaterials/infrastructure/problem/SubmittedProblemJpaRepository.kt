package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.SubmittedProblem
import org.springframework.data.jpa.repository.JpaRepository

interface SubmittedProblemJpaRepository : JpaRepository<SubmittedProblem, Long> {
    fun findAllByGivenHomeSchoolId(givenHomeSchoolId: Long): List<SubmittedProblem>
}