package org.freewheelin.homeschoolmaterials.infrastructure.homeschool

import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity.HomeSchool
import org.springframework.data.jpa.repository.JpaRepository

interface HomeSchoolJpaRepository : JpaRepository<HomeSchool, Long> {
}