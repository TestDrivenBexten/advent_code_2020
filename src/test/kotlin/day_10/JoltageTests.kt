package day_10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import util.PuzzleInputReader
import java.nio.file.Paths

class JoltageTests {
    @Test
    @Throws(Exception::class)
    fun `Small Joltage Input Should Have Correct Counts`(){
        val path = Paths.get("src/test/kotlin/day_10/small_joltage_input.txt")
        val joltageList = PuzzleInputReader.readIntListFromFile(path)

        val (oneJoltCount, threeJoltCount) = getJoltageDifference(joltageList)

        assertAll("Jolt Differences",
            { assertEquals(7,oneJoltCount)},
            { assertEquals(5,threeJoltCount)}
        )
    }

    @Test
    @Throws(Exception::class)
    fun `Medium Joltage Input Should Have Correct Counts`(){
        val path = Paths.get("src/test/kotlin/day_10/medium_joltage_input.txt")
        val joltageList = PuzzleInputReader.readIntListFromFile(path)

        val (oneJoltCount, threeJoltCount) = getJoltageDifference(joltageList)

        assertAll("Jolt Differences",
                { assertEquals(22,oneJoltCount)},
                { assertEquals(10,threeJoltCount)}
        )
    }
}