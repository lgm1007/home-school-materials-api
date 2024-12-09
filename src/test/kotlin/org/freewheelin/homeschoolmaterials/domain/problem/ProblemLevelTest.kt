package org.freewheelin.homeschoolmaterials.domain.problem

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProblemLevelTest {
    @Test
    @DisplayName("난이도 하 선택 시 문제 비율 별 개수 - totalCount: 10개일 때 하: 5개, 중: 3개, 상: 2개")
    fun allocateLowLevelProblemCount() {
        val sut = ProblemLevel.LOW

        val actual = sut.allocateProblemCount(10)

        assertThat(actual[ProblemLevel.LOW]).isEqualTo(5)
        assertThat(actual[ProblemLevel.MEDIUM]).isEqualTo(3)
        assertThat(actual[ProblemLevel.HIGH]).isEqualTo(2)
    }

    @Test
    @DisplayName("난이도 중 선택 시 문제 비율 별 개수 - totalCount: 10개일 때 하: 2개, 중: 5개, 상: 3개")
    fun allocateMediumLevelProblemCount() {
        val sut = ProblemLevel.MEDIUM

        val actual = sut.allocateProblemCount(10)

        assertThat(actual[ProblemLevel.LOW]).isEqualTo(2)
        assertThat(actual[ProblemLevel.MEDIUM]).isEqualTo(5)
        assertThat(actual[ProblemLevel.HIGH]).isEqualTo(3)
    }

    @Test
    @DisplayName("난이도 상 선택 시 문제 비율 별 개수 - totalCount: 10개일 때 하: 2개, 중: 3개, 상: 5개")
    fun allocateHighLevelProblemCount() {
        val sut = ProblemLevel.HIGH

        val actual = sut.allocateProblemCount(10)

        assertThat(actual[ProblemLevel.LOW]).isEqualTo(2)
        assertThat(actual[ProblemLevel.MEDIUM]).isEqualTo(3)
        assertThat(actual[ProblemLevel.HIGH]).isEqualTo(5)
    }
}