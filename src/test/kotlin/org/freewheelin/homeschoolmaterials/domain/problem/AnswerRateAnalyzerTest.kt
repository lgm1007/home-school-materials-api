package org.freewheelin.homeschoolmaterials.domain.problem

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.problem.dto.SubmittedProblemDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AnswerRateAnalyzerTest {
    /**
     * 학생 별 테스트용 제출 문서 데이터 생성
     * 문제 1번. 정답
     * 문제 2번. 정답
     * 문제 3번. 오답
     * 문제 4번. 오답
     * 정답률: 50%
     */
    private fun makeSubmittedProblemsToStudent(): List<SubmittedProblemDto> {
        return listOf(
            SubmittedProblemDto(1L, 123L, 1L, "3", true),
            SubmittedProblemDto(2L, 123L, 2L, "5", true),
            SubmittedProblemDto(3L, 123L, 3L, "4", false),
            SubmittedProblemDto(4L, 123L, 4L, "3", false),
        )
    }

    @Test
    @DisplayName("학습지 학습 통계 - 학생 별 정답률 계산하기")
    fun calculateStudentAnswerRateTest() {
        val sut = AnswerRateAnalyzer()
        val actual = sut.calculateStudentAnswerRate(makeSubmittedProblemsToStudent())

        assertThat(actual).isEqualTo(50.0)
    }

    /**
     * 문제 별 테스트용 제출 문서 데이터 생성
     * 문제 1번. 정답, 정답, 정답, 오답
     * 문제 1번의 정답률: 75%
     */
    private fun makeSubmittedProblemsToProblem(): List<SubmittedProblemDto> {
        return listOf(
            SubmittedProblemDto(1L, 123L, 1L, "3", true),
            SubmittedProblemDto(2L, 124L, 1L, "3", true),
            SubmittedProblemDto(3L, 125L, 1L, "3", true),
            SubmittedProblemDto(4L, 126L, 1L, "5", false),
        )
    }

    @Test
    @DisplayName("학습지 학습 통계 - 문제 별 정답률 계산하기")
    fun calculateProblemAnswerRateTest() {
        val sut = AnswerRateAnalyzer()
        val actual = sut.calculateProblemAnswerRate(1L, makeSubmittedProblemsToProblem())

        assertThat(actual).isEqualTo(75.0)
    }
}