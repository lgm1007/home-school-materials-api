package org.freewheelin.homeschoolmaterials.infrastructure.problem

import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.HomeSchoolProblem
import org.springframework.stereotype.Repository

@Repository
class HomeSchoolProblemRepositoryImpl(
	private val jpaRepository: HomeSchoolProblemJpaRepository
) : HomeSchoolProblemRepository {
	override fun getAllByHomeSchoolId(homeSchoolId: Long): List<HomeSchoolProblem> {
		return jpaRepository.findAllByHomeSchoolId(homeSchoolId)
	}

	override fun saveAll(homeSchoolProblems: List<HomeSchoolProblemDto>): List<HomeSchoolProblem> {
		return jpaRepository.saveAll(HomeSchoolProblem.listFrom(homeSchoolProblems))
	}

	override fun deleteAll() {
		jpaRepository.deleteAll()
	}
}