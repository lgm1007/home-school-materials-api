package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeSchoolServiceIntegrationTest {
    @Autowired lateinit var sut: HomeSchoolService
    @Autowired lateinit var homeSchoolRepository: HomeSchoolRepository
    @Autowired lateinit var homeSchoolProblemRepository: HomeSchoolProblemRepository
    @Autowired lateinit var givenHomeSchoolRepository: GivenHomeSchoolRepository

    @BeforeEach
    fun clearDB() {
        homeSchoolRepository.deleteAll()
        homeSchoolProblemRepository.deleteAll()
        givenHomeSchoolRepository.deleteAll()
    }

    @Test
    @DisplayName("학습지 생성 테스트: teacherId가 123, 학습지 이름이 2024 모의고사인 학습지 생성하기")
    fun createHomeSchoolTest() {
        val createDto = CreateHomeSchoolDto(
            "2024 모의고사",
            123L,
            listOf(1, 2, 3, 4, 5),
        )

        val homeSchoolId = sut.createHomeSchool(createDto)

        val homeSchool = homeSchoolRepository.getById(homeSchoolId)
        val homeSchoolProblems = homeSchoolProblemRepository.getAllByHomeSchoolId(homeSchoolId)

        assertThat(homeSchool.name).isEqualTo("2024 모의고사")
        assertThat(homeSchool.teacherId).isEqualTo(123L)
        assertThat(homeSchoolProblems.size).isEqualTo(5)
    }

    @Test
    @DisplayName("학습지 출제 테스트: homeSchoolId가 1, studentId가 123인 경우 학생에게 학습지 출제 데이터 생성하기")
    fun createGivenHomeSchoolTest() {
        val presentHomeSchoolDto = PresentHomeSchoolDto(
            1L,
            listOf(123L, 456L)
        )

        val givenHomeSchoolIds = sut.createGivenHomeSchoolByStudent(presentHomeSchoolDto).map { it.id }

        val actual = givenHomeSchoolIds.map {
            givenHomeSchoolRepository.getById(it)
        }

        assertThat(actual.size).isEqualTo(2)
        assertThat(actual[0].studentId).isEqualTo(123L)
        assertThat(actual[1].studentId).isEqualTo(456L)
    }
}