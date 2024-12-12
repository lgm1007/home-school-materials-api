package org.freewheelin.homeschoolmaterials.facade

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.homeschool.GivenHomeSchoolRepository
import org.freewheelin.homeschoolmaterials.domain.homeschool.HomeSchoolRepository
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.HomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.*
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeSubmitProblemItemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class HomeSchoolFacadeTest {
	@Autowired lateinit var sut: HomeSchoolFacade
	@Autowired lateinit var homeSchoolRepository: HomeSchoolRepository
	@Autowired lateinit var problemRepository: ProblemRepository
	@Autowired lateinit var givenHomeSchoolRepository: GivenHomeSchoolRepository
	@Autowired lateinit var submittedProblemRepository: SubmittedProblemRepository

	@BeforeEach
	fun clearDB() {
		homeSchoolRepository.deleteAll()
		problemRepository.deleteAll()
		givenHomeSchoolRepository.deleteAll()
		submittedProblemRepository.deleteAll()
	}

	private fun makeToGradeProblem() {
		val givenHomeSchool = givenHomeSchoolRepository.save(
			GivenHomeSchoolDto(0, 123L, 12345L, false)
		)
		val problems = problemRepository.saveAll(
			listOf(
				ProblemDto(0, UnitCode.UC_1580, ProblemType.SELECTION, 1, "1번 문제", "3"),
				ProblemDto(0, UnitCode.UC_1580, ProblemType.SELECTION, 2, "2번 문제", "5"),
				ProblemDto(0, UnitCode.UC_1580, ProblemType.SELECTION, 3, "3번 문제", "2"),
			)
		)
		problems.forEach {
			submittedProblemRepository.save(
				SubmittedProblemDto(0, givenHomeSchool.id, it.id, "", false)
			)
		}
	}

	@Test
	@DisplayName("채점 기능 통합 테스트")
	fun gradeProblemsTest() {
		/*
		 * 테스트용 데이터 세팅
		 * 학습지 ID (homeSchoolid): 123L
		 * 학생 ID (studentId): 12345L
		 * 1번 문제 정답: 3
		 * 2번 문제 정답: 5
		 * 3번 문제 정답: 2
 		 */
		makeToGradeProblem()

		val gradeProblemDto = GradeProblemDto(
			123L,
			12345L,
			listOf(
				GradeSubmitProblemItemDto(1L, "3"), // 제출한 답: 정답
				GradeSubmitProblemItemDto(2L, "5"), // 정답
				GradeSubmitProblemItemDto(3L, "1"), // 오답
			)
		)
		sut.gradeProblems(gradeProblemDto)

		val givenHomeSchool = givenHomeSchoolRepository.getByHomeSchoolIdAndStudentId(123L, 12345L)
		val submittedProblems = submittedProblemRepository.getAllByGivenHomeSchoolId(givenHomeSchool.id)

		assertThat(givenHomeSchool.isDone).isTrue()
		assertThat(submittedProblems.size).isEqualTo(3)
		assertThat(submittedProblems[0].isAnswered).isTrue()
		assertThat(submittedProblems[1].isAnswered).isTrue()
		assertThat(submittedProblems[2].isAnswered).isFalse()
	}

	private fun makeToAnalyze(): Long {
		val homeSchoolId = homeSchoolRepository.save(HomeSchoolDto(0, 111L, "1번 학습지", LocalDateTime.now())).id

		val givenHomeSchools = givenHomeSchoolRepository.saveAll(
			listOf(
				GivenHomeSchoolDto(0, homeSchoolId, 123L, true),
				GivenHomeSchoolDto(0, homeSchoolId, 124L, true),
				GivenHomeSchoolDto(0, homeSchoolId, 125L, true),
			)
		)

		/*
		 * 학생 1: 문제1 O, 문제2 O, 문제3 O, 문제4 O - 정답률 100.0
		 * 학생 2: 문제1 O, 문제2 O, 문제3 X, 문제4 X - 정답률 50.0
		 * 학생 3: 문제1 O, 문제2 O, 문제3 O, 문제4 X - 정답률 75.0
		 */
		submittedProblemRepository.saveAll(
			listOf(
				SubmittedProblemDto(0, givenHomeSchools[0].id, 1L, "3", true),
				SubmittedProblemDto(0, givenHomeSchools[0].id, 2L, "5", true),
				SubmittedProblemDto(0, givenHomeSchools[0].id, 3L, "2", true),
				SubmittedProblemDto(0, givenHomeSchools[0].id, 4L, "1", true),
				SubmittedProblemDto(0, givenHomeSchools[1].id, 1L, "3", true),
				SubmittedProblemDto(0, givenHomeSchools[1].id, 2L, "5", true),
				SubmittedProblemDto(0, givenHomeSchools[1].id, 3L, "3", false),
				SubmittedProblemDto(0, givenHomeSchools[1].id, 4L, "3", false),
				SubmittedProblemDto(0, givenHomeSchools[2].id, 1L, "3", true),
				SubmittedProblemDto(0, givenHomeSchools[2].id, 2L, "5", true),
				SubmittedProblemDto(0, givenHomeSchools[2].id, 3L, "2", true),
				SubmittedProblemDto(0, givenHomeSchools[2].id, 4L, "3", false),
			)
		)

		return homeSchoolId
	}

	@Test
	@DisplayName("학습지 학습 통계 데이터 구하기")
	fun analyzeHomeSchool() {
		val homeSchoolId = makeToAnalyze()

		val actual = sut.analyzeHomeSchool(homeSchoolId)

		assertThat(actual.name).isEqualTo("1번 학습지")
		assertThat(actual.studentDataList.size).isEqualTo(3)
		assertThat(actual.problemDataList.size).isEqualTo(4)
	}
}