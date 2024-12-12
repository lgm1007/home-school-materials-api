package org.freewheelin.homeschoolmaterials.api.homeschool

import io.micrometer.core.instrument.Meter.Id
import org.freewheelin.homeschoolmaterials.api.homeschool.request.CreateHomeSchoolRequest
import org.freewheelin.homeschoolmaterials.api.homeschool.request.PresentHomeSchoolRequest
import org.freewheelin.homeschoolmaterials.api.homeschool.response.AnalyzeHomeSchoolProblemResponse
import org.freewheelin.homeschoolmaterials.api.homeschool.response.AnalyzeHomeSchoolResponse
import org.freewheelin.homeschoolmaterials.api.homeschool.response.AnalyzeHomeSchoolStudentResponse
import org.freewheelin.homeschoolmaterials.api.homeschool.response.PresentHomeSchoolResponse
import org.freewheelin.homeschoolmaterials.domain.homeschool.HomeSchoolService
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.facade.HomeSchoolFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/home-schools")
@RestController
class HomeSchoolApi(
	private val homeSchoolFacade: HomeSchoolFacade,
	private val homeSchoolService: HomeSchoolService
) {
	/**
	 * 학습지 생성 API
	 */
	@PostMapping("")
	fun createHomeSchool(
		@RequestBody request: CreateHomeSchoolRequest
	): ResponseEntity<Long> {
		return ResponseEntity.ok(
			homeSchoolService.createHomeSchool(
				CreateHomeSchoolDto.from(request)
			)
		)
	}

	/**
	 * 학생에게 학습지 출제 API
	 */
	@PostMapping("/present")
	fun presentHomeSchool(
		@RequestBody request: PresentHomeSchoolRequest
	): ResponseEntity<PresentHomeSchoolResponse> {
		return ResponseEntity.ok(
			PresentHomeSchoolResponse.from(
				homeSchoolFacade.presentHomeSchool(
					PresentHomeSchoolDto.from(request)
				)
			)
		)
	}

	/**
	 * 학습지 학습 통계 분석 API
	 */
	@GetMapping("/analyze/{homeSchoolId}")
	fun analyzeHomeSchool(
		@PathVariable("homeSchoolId") homeSchoolId: Long,
	): ResponseEntity<AnalyzeHomeSchoolResponse> {
		return ResponseEntity.ok(
			AnalyzeHomeSchoolResponse.from(
				homeSchoolFacade.analyzeHomeSchool(homeSchoolId)
			)
		)
	}
}