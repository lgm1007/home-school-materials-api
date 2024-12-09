package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.HomeSchoolProblem
import org.springframework.data.jpa.repository.JpaRepository

interface HomeSchoolProblemJpaRepository : JpaRepository<HomeSchoolProblem, Long> {
	fun findAllByHomeSchoolId(homeSchoolId: Long): List<HomeSchoolProblem>
}