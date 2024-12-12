package org.freewheelin.homeschoolmaterials.domain.homeschool

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.CreateHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.PresentHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.HomeSchoolProblemRepository
import org.freewheelin.homeschoolmaterials.infrastructure.homeschool.GivenHomeSchoolRepositoryImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeSchoolServiceIntegrationTest {
    @Autowired
    private lateinit var givenHomeSchoolRepositoryImpl: GivenHomeSchoolRepositoryImpl
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

    @Test
    @DisplayName("학습지 출제 중 이미 해당 학습지를 출제한 학생의 경우에는 출제하지 않는지 테스트")
    fun createGivenHomeSchoolIsAlreadyExistTest() {
        // 학생 ID: 123인 학생에게 이미 ID 1인 학습지 출제
        givenHomeSchoolRepository.save(
            GivenHomeSchoolDto(0, 1L, 123L, false)
        )

        val presentHomeSchoolDto = PresentHomeSchoolDto(
            1L,
            listOf(123L, 456L)
        )

        val actual = sut.createGivenHomeSchoolByStudent(presentHomeSchoolDto)

        assertThat(actual.size).isEqualTo(1)
        assertThat(actual[0].studentId).isEqualTo(456L)
    }
}