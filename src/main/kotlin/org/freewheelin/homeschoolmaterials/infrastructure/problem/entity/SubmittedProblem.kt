package org.freewheelin.homeschoolmaterials.infrastructure.problem.entity

import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class SubmittedProblem(
    val givenHomeSchoolId: Long,
    val problemId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var submitAnswer: String = ""

    var isAnswered: Boolean = false

    fun gradingProblem(submitAnswer: String, answer: String) {
        this.submitAnswer = submitAnswer

        if (this.submitAnswer == answer) {
            isAnswered = true
        }
    }

    companion object {
        fun from(dto: SubmittedProblemDto): SubmittedProblem {
            return SubmittedProblem(
                dto.givenHomeSchoolId,
                dto.problemId
            )
        }

        fun listFrom(dtos: List<SubmittedProblemDto>): List<SubmittedProblem> {
            return dtos.map { from(it) }
        }
    }
}