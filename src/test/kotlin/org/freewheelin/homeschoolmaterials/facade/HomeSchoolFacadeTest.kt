package org.freewheelin.homeschoolmaterials.facade

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.homeschool.GivenHomeSchoolRepository
import org.freewheelin.homeschoolmaterials.domain.homeschool.dto.GivenHomeSchoolDto
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.ProblemType
import org.freewheelin.homeschoolmaterials.domain.problem.SubmittedProblemRepository
import org.freewheelin.homeschoolmaterials.domain.problem.UnitCode
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.GradeSubmitProblemItemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeSchoolFacadeTest {
	@Autowired lateinit var homeSchoolFacade: HomeSchoolFacade
	@Autowired lateinit var givenHomeSchoolRepository: GivenHomeSchoolRepository
	@Autowired lateinit var problemRepository: ProblemRepository
	@Autowired lateinit var submittedProblemRepository: SubmittedProblemRepository

	@BeforeEach
	fun clearDB() {
		givenHomeSchoolRepository.deleteAll()
		problemRepository.deleteAll()
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
		 * 학생 ID (studentId): 12345L
		 * 학습지 ID (homeSchoolid): 123L
		 * 1번 문제 정답: 3
		 * 2번 문제 정답: 5
		 * 3번 문제 정답: 2
 		 */
		makeToGradeProblem()

		val gradeProblemDto = GradeProblemDto(
			12345L,
			123L,
			listOf(
				GradeSubmitProblemItemDto(1L, "3"), // 제출한 답: 정답
				GradeSubmitProblemItemDto(2L, "5"), // 정답
				GradeSubmitProblemItemDto(3L, "1"), // 오답
			)
		)
		homeSchoolFacade.gradeProblems(gradeProblemDto)

		val givenHomeSchool = givenHomeSchoolRepository.getByHomeSchoolIdAndStudentId(123L, 12345L)
		val submittedProblems = submittedProblemRepository.getAllByGivenHomeSchoolId(givenHomeSchool.id)

		assertThat(givenHomeSchool.isDone).isTrue()
		assertThat(submittedProblems.size).isEqualTo(3)
		assertThat(submittedProblems[0].isAnswered).isTrue()
		assertThat(submittedProblems[1].isAnswered).isTrue()
		assertThat(submittedProblems[2].isAnswered).isFalse()
	}
}