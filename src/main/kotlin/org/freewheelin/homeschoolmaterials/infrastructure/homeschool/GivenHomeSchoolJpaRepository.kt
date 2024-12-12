package org.freewheelin.homeschoolmaterials.infrastructure.homeschool

import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.GivenHomeSchool
import org.springframework.data.jpa.repository.JpaRepository

interface GivenHomeSchoolJpaRepository : JpaRepository<GivenHomeSchool, Long> {
	fun findByHomeSchoolIdAndStudentId(homeSchoolId: Long, studentId: Long): GivenHomeSchool?
}