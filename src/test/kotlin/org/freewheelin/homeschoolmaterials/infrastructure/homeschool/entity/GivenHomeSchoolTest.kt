package org.freewheelin.homeschoolmaterials.infrastructure.homeschool.entity

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GivenHomeSchoolTest {
	@Test
	@DisplayName("학생이 제출한 학습지에 대해 제출 완료 상태 업데이트 기능 테스트")
	fun givenHomeSchoolIsDoneTest() {
		val sut = GivenHomeSchool(homeSchoolId = 1L, studentId = 123L)
		sut.setIsDoneTrue()

		assertThat(sut.isDone).isTrue()
	}
}