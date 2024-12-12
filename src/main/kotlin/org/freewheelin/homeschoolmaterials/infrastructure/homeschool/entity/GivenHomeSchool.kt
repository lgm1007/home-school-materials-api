package org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class GivenHomeSchool(
    val homeSchoolId: Long,
    val studentId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var isDone = false

    fun setIsDoneTrue() {
        isDone = true
    }

    constructor(homeSchoolId: Long, studentId: Long, isDone: Boolean) : this(homeSchoolId, studentId) {
        this.isDone = isDone
    }

    companion object {
        fun from(dto: GivenHomeSchoolDto): GivenHomeSchool {
            return GivenHomeSchool(
                dto.homeSchoolId,
                dto.studentId,
                dto.isDone
            )
        }

        fun listFrom(dtos: List<GivenHomeSchoolDto>): List<GivenHomeSchool> {
            return dtos.map { from(it) }
        }
    }
}