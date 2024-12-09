package org.freewheelin.homeschoolmaterials.domain.problem

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class ProblemServiceUnitTest {
    @Mock
    lateinit var problemRepository: ProblemRepository

    @InjectMocks
    lateinit var sut: ProblemService

    @Test
    @DisplayName("문제 조회 Mock 테스트 - 조회된 총 문제: 난이도 하 10개, 중 10개, 상 10개, 선택된 난이도 : 하, totalCount : 20")
    fun getProblemsByLowLevelTest() {
        `when`(problemRepository.getAllByUnitCodesAndProblemType(any(), any()))
            .thenReturn(makeProblems(UnitCode.UC_1580, ProblemType.SELECTION))

        val param = FindProblemParam(20, listOf(UnitCode.UC_1580), ProblemType.SELECTION, ProblemLevel.LOW)

        val actual = sut.getProblems(param)

        assertThat(actual.size).isEqualTo(20)
        assertThat(actual.filter { ProblemLevel.LOW.levels.contains(it.level) }.size).isEqualTo(10) // 조회된 문제 중 난이도 하 인 문제 개수
        assertThat(actual.filter { ProblemLevel.MEDIUM.levels.contains(it.level) }.size).isEqualTo(6) // 조회된 문제 중 난이도 중 인 문제 개수
        assertThat(actual.filter { ProblemLevel.HIGH.levels.contains(it.level) }.size).isEqualTo(4) // 조회된 문제 중 난이도 상 인 문제 개수
    }

    @Test
    @DisplayName("문제 조회 Mock 테스트 - 조회된 총 문제: 난이도 하 10개, 중 10개, 상 10개, 선택된 난이도 : 중, totalCount : 20")
    fun getProblemsByMediumLevelTest() {
        `when`(problemRepository.getAllByUnitCodesAndProblemType(any(), any()))
            .thenReturn(makeProblems(UnitCode.UC_1580, ProblemType.SELECTION))

        val param = FindProblemParam(20, listOf(UnitCode.UC_1580), ProblemType.SELECTION, ProblemLevel.MEDIUM)

        val actual = sut.getProblems(param)

        assertThat(actual.size).isEqualTo(20)
        assertThat(actual.filter { ProblemLevel.LOW.levels.contains(it.level) }.size).isEqualTo(4) // 조회된 문제 중 난이도 하 인 문제 개수
        assertThat(actual.filter { ProblemLevel.MEDIUM.levels.contains(it.level) }.size).isEqualTo(10) // 조회된 문제 중 난이도 중 인 문제 개수
        assertThat(actual.filter { ProblemLevel.HIGH.levels.contains(it.level) }.size).isEqualTo(6) // 조회된 문제 중 난이도 상 인 문제 개수
    }

    @Test
    @DisplayName("문제 조회 Mock 테스트 - 조회된 총 문제: 난이도 하 10개, 중 10개, 상 10개, 선택된 난이도 : 상, totalCount : 20")
    fun getProblemsByHighLevelTest() {
        `when`(problemRepository.getAllByUnitCodesAndProblemType(any(), any()))
            .thenReturn(makeProblems(UnitCode.UC_1580, ProblemType.SELECTION))

        val param = FindProblemParam(20, listOf(UnitCode.UC_1580), ProblemType.SELECTION, ProblemLevel.HIGH)

        val actual = sut.getProblems(param)

        assertThat(actual.size).isEqualTo(20)
        assertThat(actual.filter { ProblemLevel.LOW.levels.contains(it.level) }.size).isEqualTo(4) // 조회된 문제 중 난이도 하 인 문제 개수
        assertThat(actual.filter { ProblemLevel.MEDIUM.levels.contains(it.level) }.size).isEqualTo(6) // 조회된 문제 중 난이도 중 인 문제 개수
        assertThat(actual.filter { ProblemLevel.HIGH.levels.contains(it.level) }.size).isEqualTo(10) // 조회된 문제 중 난이도 상 인 문제 개수
    }

    private fun makeProblems(unitCode: UnitCode, problemType: ProblemType): List<Problem> {
        return listOf(
            Problem(unitCode, problemType, 1, "난이도 하 문제 1", "1"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 2", "2"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 3", "3"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 4", "4"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 5", "5"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 6", "1"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 7", "2"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 8", "3"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 9", "4"),
            Problem(unitCode, problemType, 1, "난이도 하 문제 10", "5"),
            Problem(unitCode, problemType, 2, "난이도 중 문제 1", "1"),
            Problem(unitCode, problemType, 3, "난이도 중 문제 2", "2"),
            Problem(unitCode, problemType, 2, "난이도 중 문제 3", "3"),
            Problem(unitCode, problemType, 3, "난이도 중 문제 4", "4"),
            Problem(unitCode, problemType, 2, "난이도 중 문제 5", "5"),
            Problem(unitCode, problemType, 3, "난이도 중 문제 6", "1"),
            Problem(unitCode, problemType, 3, "난이도 중 문제 7", "2"),
            Problem(unitCode, problemType, 4, "난이도 중 문제 8", "3"),
            Problem(unitCode, problemType, 4, "난이도 중 문제 9", "4"),
            Problem(unitCode, problemType, 4, "난이도 중 문제 10", "5"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 1", "1"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 2", "2"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 3", "3"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 4", "4"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 5", "5"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 6", "1"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 7", "2"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 8", "3"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 9", "4"),
            Problem(unitCode, problemType, 5, "난이도 상 문제 10", "5"),
        )
    }
}