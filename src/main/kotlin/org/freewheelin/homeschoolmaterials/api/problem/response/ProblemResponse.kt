package org.freewheelin.homeschoolmaterials.api.problem.response

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto

data class ProblemResponse(
	val id: Long,
	val unitCode: UnitCode,
	val problemType: ProblemType,
	val level: Int,
) {
	lateinit var question: String
		private set

	lateinit var answer: String
		private set

	constructor(id: Long, unitCode: UnitCode, problemType: ProblemType, level: Int, question: String) : this(id, unitCode, problemType, level) {
		this.question = question
	}

	constructor(id: Long, unitCode: UnitCode, problemType: ProblemType, level: Int, question: String, answer: String) : this(id, unitCode, problemType, level) {
		this.question = question
		this.answer = answer
	}

	companion object {
		fun toStudentResponse(dto: ProblemDto): ProblemResponse {
			return ProblemResponse(
				dto.id,
				dto.unitCode,
				dto.problemType,
				dto.level,
				dto.question
			)
		}

		fun listToStudentResponse(dtos: List<ProblemDto>): List<ProblemResponse> {
			return dtos.map { toStudentResponse(it) }
		}

		fun toTeacherResponse(dto: ProblemDto): ProblemResponse {
			return ProblemResponse(
				dto.id,
				dto.unitCode,
				dto.problemType,
				dto.level,
				dto.question,
				dto.answer
			)
		}

		fun listToTeacherResponse(dtos: List<ProblemDto>): List<ProblemResponse> {
			return dtos.map { toTeacherResponse(it) }
		}
	}
}