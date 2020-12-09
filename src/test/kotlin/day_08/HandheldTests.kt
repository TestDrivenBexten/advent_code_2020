package day_08

import org.junit.jupiter.api.Assertions.*
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
    fun `Small Program Should Not Terminate`(){
        val path = Paths.get("src/test/kotlin/day_08/small_instruction_set.txt")
        val instructionList = PuzzleInputReader.readStringListFromFile(path)

        val handheld = Handheld()
        handheld.loadProgram(instructionList)

        assertFalse(handheld.willProgramTerminate())
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

    @Test
    @Throws(Exception::class)
    fun `Small Finite Program Should Terminate`(){
        val path = Paths.get("src/test/kotlin/day_08/small_finite_instruction_set.txt")
        val instructionList = PuzzleInputReader.readStringListFromFile(path)

        val handheld = Handheld()
        handheld.loadProgram(instructionList)
        assertTrue(handheld.willProgramTerminate())
    }

    @Test
    @Throws(Exception::class)
    fun `Fix Puzzle Program and Find Accumulator Value`(){
        val path = Paths.get("src/test/kotlin/day_08/puzzle_instruction_set.txt")
        val instructionList = PuzzleInputReader.readStringListFromFile(path)

        val handheld = Handheld()
        handheld.loadProgram(instructionList)
        handheld.fixProgram()
        handheld.runProgram()
        assertEquals(501,handheld.getAccumulatedValue())
    }
}