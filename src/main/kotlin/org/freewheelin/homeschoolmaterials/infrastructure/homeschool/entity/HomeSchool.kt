package org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity

import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class HomeSchool(
    val teacherId: Long,
    val name: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.now()

    companion object {
        fun from(dto: HomeSchoolDto): HomeSchool {
            return HomeSchool(
                dto.teacherId,
                dto.name,
            )
        }

        fun listFrom(dtos: List<HomeSchoolDto>): List<HomeSchool> {
            return dtos.map { from(it) }
        }
    }
}