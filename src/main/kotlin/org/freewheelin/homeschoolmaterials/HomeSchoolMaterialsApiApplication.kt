package org.freewheelin.homeschoolmaterials

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class HomeSchoolMaterialsApiApplication

fun main(args: Array<String>) {
	runApplication<HomeSchoolMaterialsApiApplication>(*args)
}
