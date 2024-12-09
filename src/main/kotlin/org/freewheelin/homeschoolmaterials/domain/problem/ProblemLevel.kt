package org.freewheelin.homeschoolmaterials.domain.problem

enum class ProblemLevel(val levels: List<Int>) {
	LOW(listOf(1)) {
		override fun allocateProblemCount(totalCount: Int): Map<ProblemLevel, Int> {
			val lowCount = (totalCount * 0.5).toInt()
			val mediumCount = (totalCount * 0.3).toInt()
			val highCount = totalCount - (lowCount + mediumCount)

			return mapOf(
				LOW to lowCount,
				MEDIUM to mediumCount,
				HIGH to highCount
			)
		}
	},
	MEDIUM(listOf(2, 3, 4)) {
		override fun allocateProblemCount(totalCount: Int): Map<ProblemLevel, Int> {
			val lowCount = (totalCount * 0.2).toInt()
			val mediumCount = (totalCount * 0.5).toInt()
			val highCount = totalCount - (lowCount + mediumCount)

			return mapOf(
				LOW to lowCount,
				MEDIUM to mediumCount,
				HIGH to highCount
			)
		}
	},
	HIGH(listOf(5)) {
		override fun allocateProblemCount(totalCount: Int): Map<ProblemLevel, Int> {
			val lowCount = (totalCount * 0.2).toInt()
			val mediumCount = (totalCount * 0.3).toInt()
			val highCount = totalCount - (lowCount + mediumCount)

			return mapOf(
				LOW to lowCount,
				MEDIUM to mediumCount,
				HIGH to highCount
			)
		}
	};

	/**
	 * 난이도 별 문제 개수 비율 계산하는 추상메서드
	 */
	abstract fun allocateProblemCount(totalCount: Int): Map<ProblemLevel, Int>
}