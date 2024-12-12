package org.freewheelin.homeschoolmaterials.infrastructure.homeschool

import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.GivenHomeSchool
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GivenHomeSchoolJpaRepository : JpaRepository<GivenHomeSchool, Long> {
	fun findByHomeSchoolIdAndStudentId(homeSchoolId: Long, studentId: Long): GivenHomeSchool?

	@Query("SELECT ghs FROM GivenHomeSchool ghs WHERE ghs.homeSchoolId = :homeSchoolId AND ghs.isDone = :isDone")
	fun findAllByHomeSchoolIdAndIsDone(
		@Param("homeSchoolId") homeSchoolId: Long,
		@Param("isDone") isDone: Boolean
	): List<GivenHomeSchool>
}