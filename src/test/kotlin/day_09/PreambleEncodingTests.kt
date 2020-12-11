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
        val numberList = PuzzleInputReader.readLongListFromFile(path)

        val nonMatchingNumber = findNonMatchingNumberFromList(5,numberList)
        assertEquals(127,nonMatchingNumber)
    }

    @Test
    @Throws(Exception::class)
    fun `Solve Puzzle Preamble Part 1`(){
        val path = Paths.get("src/test/kotlin/day_09/puzzle_preamble.txt")
        val numberList = PuzzleInputReader.readLongListFromFile(path)

        val nonMatchingNumber = findNonMatchingNumberFromList(25,numberList)
        assertEquals(373803594,nonMatchingNumber)
    }

    @Test
    @Throws(Exception::class)
    fun `Small Preamble Should Have 62 for Encryption Weakness`(){
        val path = Paths.get("src/test/kotlin/day_09/small_preamble.txt")
        val numberList = PuzzleInputReader.readLongListFromFile(path)

        val encryptionWeakness = findEncryptionWeakness(5,numberList)
        assertEquals(62,encryptionWeakness)
    }

    @Test
    @Throws(Exception::class)
    fun `Solve Puzzle Preamble Part 2`(){
        val path = Paths.get("src/test/kotlin/day_09/puzzle_preamble.txt")
        val numberList = PuzzleInputReader.readLongListFromFile(path)

        val encryptionWeakness = findEncryptionWeakness(25,numberList)
        assertEquals(51152360,encryptionWeakness)
    }
}