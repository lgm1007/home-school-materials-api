package org.freewheelin.homeschoolmaterials.api.problem

import org.freewheelin.homeschoolmaterials.api.problem.request.GradeProblemRequest
import org.freewheelin.homeschoolmaterials.api.problem.response.GradeProblemResultResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemListResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemResponse
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemLevel
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemService
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemDto
import org.freewheelin.homeschoolmaterials.facade.HomeSchoolFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/problems")
@RestController
class ProblemApi(
	private val problemService: ProblemService,
	private val homeSchoolFacade: HomeSchoolFacade
) {
	/**
	 * 문제 조회 API
	 */
	@GetMapping("")
	fun getProblems(
		@RequestParam totalCount: Int,
		@RequestParam unitCodes: List<UnitCode>,
		@RequestParam problemType: ProblemType,
		@RequestParam problemLevel: ProblemLevel
	): ResponseEntity<ProblemListResponse> {
		return ResponseEntity.ok(
			ProblemListResponse(
				ProblemResponse.listToTeacherResponse(
					problemService.getProblems(
						FindProblemParam(totalCount, unitCodes, problemType, problemLevel)
					)
				)
			)
		)
	}

	/**
	 * 학습지 문제 조회 API
	 */
	@GetMapping("/{homeSchoolId}")
	fun getProblemByHomeSchoolId(
		@PathVariable("homeSchoolId") homeSchoolId: Long
	): ResponseEntity<ProblemListResponse> {
		return ResponseEntity.ok(
			ProblemListResponse(
				ProblemResponse.listToStudentResponse(
					problemService.getProblemByHomeSchoolId(homeSchoolId)
				)
			)
		)
	}

	/**
	 * 채점하기 API
	 */
	@PutMapping("/grade")
	fun gradeProblems(
		@RequestBody request: GradeProblemRequest
	): ResponseEntity<GradeProblemResultResponse> {
		return ResponseEntity.ok(
			GradeProblemResultResponse.from(
				homeSchoolFacade.gradeProblems(
					GradeProblemDto.from(request)
				)
			)
		)
	}
}