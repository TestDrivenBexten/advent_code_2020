package day_06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GroupAnswerKotlinTests {
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