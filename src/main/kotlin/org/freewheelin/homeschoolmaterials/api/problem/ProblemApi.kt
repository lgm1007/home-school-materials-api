package org.freewheelin.homeschoolmaterials.api.problem

import org.freewheelin.homeschoolmaterials.api.problem.request.GradeProblemRequest
import org.freewheelin.homeschoolmaterials.api.problem.response.GradeProblemResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.GradeProblemResultResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemListResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemResponse
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemLevel
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemService
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class ProblemApi(
	private val problemService: ProblemService
) {
	/**
	 * 문제 조회 API
	 */
	@GetMapping("/problems")
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
	@GetMapping("/problems/{homeSchoolId}")
	fun getProblemByHomeSchoolId(
		@PathVariable("homeSchoolId") homeSchoolId: Long
	): ResponseEntity<ProblemListResponse> {
		return ResponseEntity.ok(
			ProblemListResponse(
				listOf(
					ProblemResponse(1, "유형코드", ProblemType.SELECTION, 1, "문제1"),
					ProblemResponse(2, "유형코드", ProblemType.SELECTION, 2, "문제2"),
					ProblemResponse(3, "유형코드", ProblemType.SUBJECTIVE, 3, "문제3"),
				)
			)
		)
	}

	/**
	 * 채점하기 API
	 */
	@PutMapping("/problems/grade")
	fun gradeProblems(
		@RequestBody gradeProblem: GradeProblemRequest
	): ResponseEntity<GradeProblemResultResponse> {
		return ResponseEntity.ok(
			GradeProblemResultResponse(
				12345L,
				1L,
				listOf(
					GradeProblemResponse(1, "3", "3", true),
					GradeProblemResponse(2, "5", "4", false),
				)
			)
		)
	}
}