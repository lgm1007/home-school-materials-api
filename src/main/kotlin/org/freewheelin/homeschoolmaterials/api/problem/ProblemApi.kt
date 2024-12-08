package org.freewheelin.homeschoolmaterials.api.problem

import org.freewheelin.homeschoolmaterials.api.problem.request.GradeProblemRequest
import org.freewheelin.homeschoolmaterials.api.problem.response.GradeProblemResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.GradeProblemResultResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemListResponse
import org.freewheelin.homeschoolmaterials.api.problem.response.ProblemResponse
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemLevel
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class ProblemApi {
	/**
	 * 문제 조회 API
	 */
	@GetMapping("/problems")
	fun getProblems(
		@RequestParam totalCount: Int,
		@RequestParam unitCodes: List<String>,
		@RequestParam problemType: ProblemType,
		@RequestParam problemLevel: ProblemLevel
	): ProblemListResponse {
		return ProblemListResponse(
			listOf(
				ProblemResponse(1, "유형코드", ProblemType.SELECTION, 1, "문제1", "3"),
				ProblemResponse(2, "유형코드", ProblemType.SELECTION, 2, "문제2", "5"),
			)
		)
	}

	/**
	 * 학습지 문제 조회 API
	 */
	@GetMapping("/problems/{homeSchoolId}")
	fun getProblemByHomeSchoolId(@PathVariable homeSchoolId: Long): ProblemListResponse {
		return ProblemListResponse(
			listOf(
				ProblemResponse(1, "유형코드", ProblemType.SELECTION, 1, "문제1"),
				ProblemResponse(2, "유형코드", ProblemType.SELECTION, 2, "문제2"),
				ProblemResponse(3, "유형코드", ProblemType.SUBJECTIVE, 3, "문제3"),
			)
		)
	}

	/**
	 * 채점하기 API
	 */
	@PutMapping("/grade/problems")
	fun gradeProblems(
		@RequestBody gradeProblem: GradeProblemRequest
	): GradeProblemResultResponse {
		return GradeProblemResultResponse(
			12345L,
			1L,
			listOf(
				GradeProblemResponse(1, "3", "3", true),
				GradeProblemResponse(2, "5", "4", false),
			)
		)
	}
}