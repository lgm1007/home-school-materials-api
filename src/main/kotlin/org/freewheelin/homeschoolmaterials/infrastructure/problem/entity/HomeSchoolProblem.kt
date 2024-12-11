package org.freewheelin.homeschoolmaterials.infrastructure.problem.entity

import org.freewheelin.homeschoolmaterials.domain.problem.dto.HomeSchoolProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.HomeSchool
import javax.persistence.*

@Entity
class HomeSchoolProblem(
	val homeSchoolId: Long,
	val problemId: Long
) {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long = 0

	companion object {
		fun from(dto: HomeSchoolProblemDto): HomeSchoolProblem {
			return HomeSchoolProblem(
				dto.homeSchoolId,
				dto.problemId
			)
		}

		fun listFrom(dtos: List<HomeSchoolProblemDto>): List<HomeSchoolProblem> {
			return dtos.map { from(it) }
		}
	}
}