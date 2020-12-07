package day_06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GroupAnswerKotlinTests {
    @ParameterizedTest(name = "Group spec {0} should have {1} unique answers")
    @CsvSource(
            "'abc', 3",
            "'a\nb\nc', 3",
            "'ab\nac', 3",
            "'a\na\na\na', 1",
            "'b', 1"
    )
    fun `Group Unique Answer Count Tests`(groupAnswer: String, expectedCount: Int) {
        val actualCount = GroupAnswer.calculateGroupAnswerCount(groupAnswer)
        assertEquals(expectedCount, actualCount)
    }

    @ParameterizedTest(name = "Group spec {0} should have {1} unanimous answers")
    @CsvSource(
            "'abc', 3",
            "'a\nb\nc', 0",
            "'ab\nac', 1",
            "'a\na\na\na', 1",
            "'b', 1"
    )
    fun `Group Unanimous Answer Count Tests`(groupAnswer: String, expectedCount: Int) {
        println(groupAnswer)
        val actualCount = GroupAnswer.calculateUnanimousGroupAnswerCount(groupAnswer)
        assertEquals(expectedCount, actualCount)
    }
}