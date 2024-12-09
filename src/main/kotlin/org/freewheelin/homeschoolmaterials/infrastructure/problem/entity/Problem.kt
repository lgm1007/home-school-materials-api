package org.freewheelin.homeschoolmaterials.infrastructure.problem.entity

import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import javax.persistence.*

@Entity
@Table(name = "Problem", indexes = [Index(name = "idx_unitCode_problemType", columnList = "unitCode, problemType")])
class Problem(
    @Enumerated(EnumType.STRING) val unitCode: UnitCode,
    @Enumerated(EnumType.STRING) val problemType: ProblemType,
    val level: Int,
    val question: String,
    val answer: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    companion object {
        fun from(dto: ProblemDto): Problem {
            return Problem(
                dto.unitCode,
                dto.problemType,
                dto.level,
                dto.question,
                dto.answer
            )
        }

        fun listFrom(dtos: List<ProblemDto>): List<Problem> {
            return dtos.map { from(it) }
        }
    }
}