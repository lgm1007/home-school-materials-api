package org.freewheelin.homeschoolmaterials.infrastructure.problem.entity

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SubmittedProblemTest {
	@Test
	@DisplayName("제출한 문제에 대해 정답인지 채점하는 기능 테스트")
	fun gradingProblemTest() {
		val sut = SubmittedProblem(givenHomeSchoolId = 123L, problemId = 1L)

		sut.gradingProblem("5", "5")

		assertThat(sut.submitAnswer).isEqualTo("5")
		assertThat(sut.isAnswered).isTrue()
	}
}