package day_10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import util.PuzzleInputReader
import java.nio.file.Paths

class JoltageTests {
    @Test
    @Throws(Exception::class)
    fun `Small Preamble Should Have 127 Without a Sum`(){
        val path = Paths.get("src/test/kotlin/day_10/small_joltage_input.txt")
        val joltageList = PuzzleInputReader.readIntListFromFile(path)

        val (oneJoltCount, threeJoltCount) = getJoltageDifference(joltageList)

        assertAll("Jolt Differences",
            { assertEquals(7,oneJoltCount)},
            { assertEquals(5,threeJoltCount)}
        )
    }
}