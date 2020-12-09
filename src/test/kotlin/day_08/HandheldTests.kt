package day_08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class HandheldTests {

    @Test
    @Throws(Exception::class)
    fun `Small Program Should End With 5`(){
        val path = Paths.get("src/test/kotlin/day_08/small_instruction_set.txt")
        val instructionList = PuzzleInputReader.readStringListFromFile(path)

        val handheld = Handheld()
        handheld.loadProgram(instructionList)
        handheld.runProgram()
        val accumulatedValue = handheld.getAccumulatedValue()

        assertEquals(5,accumulatedValue)
    }

    @Test
    @Throws(Exception::class)
    fun `Puzzle Program Should End With 1217`(){
        val path = Paths.get("src/test/kotlin/day_08/puzzle_instruction_set.txt")
        val instructionList = PuzzleInputReader.readStringListFromFile(path)

        val handheld = Handheld()
        handheld.loadProgram(instructionList)
        handheld.runProgram()
        val accumulatedValue = handheld.getAccumulatedValue()

        assertEquals(1217,accumulatedValue)
    }
}