package org.freewheelin.homeschoolmaterials.domain.problem

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.freewheelin.homeschoolmaterials.domain.problem.dto.FindProblemParam
import org.freewheelin.homeschoolmaterials.domain.problem.dto.ProblemDto
import org.freewheelin.homeschoolmaterials.infrastructure.problem.entity.Problem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProblemServiceIntegrationTest {
    @Autowired lateinit var sut: ProblemService
    @Autowired lateinit var problemRepository: ProblemRepository

    @BeforeEach
    fun clearDB() {
        problemRepository.deleteAll()
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

    @Test
    @DisplayName("총 저장된 문제가 난이도 하 10개, 중 10개, 상 10개에서 선택된 난이도: 하, totalCount: 20일 때 문제 조회하기")
    fun getProblemsTest() {
        problemRepository.saveAll(ProblemDto.listFrom(makeProblems(UnitCode.UNIT_7139, ProblemType.SELECTION)))
        val param = FindProblemParam(20, listOf(UnitCode.UNIT_7139), ProblemType.SELECTION, ProblemLevel.LOW)

        val actual = sut.getProblems(param)

        assertThat(actual.size).isEqualTo(20)
        assertThat(actual.filter { ProblemLevel.LOW.levels.contains(it.level) }.size).isEqualTo(10) // 조회된 문제 중 난이도 하 인 문제 개수
        assertThat(actual.filter { ProblemLevel.MEDIUM.levels.contains(it.level) }.size).isEqualTo(6) // 조회된 문제 중 난이도 중 인 문제 개수
        assertThat(actual.filter { ProblemLevel.HIGH.levels.contains(it.level) }.size).isEqualTo(4) // 조회된 문제 중 난이도 상 인 문제 개수
    }

}