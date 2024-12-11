package org.freewheelin.homeschoolmaterials.domain.problem.dto

import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.HomeSchoolProblem

data class HomeSchoolProblemDto(
	val id: Long,
	val homeSchoolId: Long,
	val problemId: Long,
) {
	companion object {
		fun from(homeSchoolProblem: HomeSchoolProblem): HomeSchoolProblemDto {
			return HomeSchoolProblemDto(
				homeSchoolProblem.id,
				homeSchoolProblem.homeSchoolId,
				homeSchoolProblem.problemId
			)
		}

		fun listFrom(homeSchoolProblems: List<HomeSchoolProblem>): List<HomeSchoolProblemDto> {
			return homeSchoolProblems.map { from(it) }
		}
	}
}