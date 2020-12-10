package day_09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class PreambleEncodingTests {

    @Test
    @Throws(Exception::class)
    fun `Small Preamble Should Have 127 Without a Sum`(){
        val path = Paths.get("src/test/kotlin/day_09/small_preamble.txt")
        val numberList = PuzzleInputReader.readIntListFromFile(path)

        val nonMatchingNumber = findNonMatchingNumberFromList(5,numberList)
        assertEquals(127,nonMatchingNumber)
    }
}