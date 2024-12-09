package org.freewheelin.homeschoolmaterials.domain.problem

import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.HomeSchoolProblem

interface HomeSchoolProblemRepository {
	fun getAllByHomeSchoolId(homeSchoolId: Long): List<HomeSchoolProblem>

	fun saveAll(homeSchoolProblems: List<HomeSchoolProblemDto>): List<HomeSchoolProblem>

	fun deleteAll()
}