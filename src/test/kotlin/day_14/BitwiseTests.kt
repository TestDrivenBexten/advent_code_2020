package day_14

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class BitwiseTests {

    @Test
    fun `Bitmask on 11 should return 73`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(11,bitmask)
        assertEquals(73,transformedValue)
    }

    @Test
    fun `Bitmask on 101 should return 101`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(101,bitmask)
        assertEquals(101,transformedValue)
    }

    @Test
    fun `Bitmask on 0 should return 64`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(0,bitmask)
        assertEquals(64,transformedValue)
    }

    @Test
    @Throws(Exception::class)
    fun `Small Bit Program Should Have Sum of 165`(){
        val path = Paths.get("src/test/kotlin/day_14/small_bit_program.txt")
        val bitProgram = PuzzleInputReader.readStringListFromFile(path)

        val sum = findMemorySumOfBitProgram(bitProgram)
        assertEquals(165,sum)
    }

    @Test
    @Throws(Exception::class)
    fun `Puzzle Bit Program Test`(){
        val path = Paths.get("src/test/kotlin/day_14/puzzle_bit_program.txt")
        val bitProgram = PuzzleInputReader.readStringListFromFile(path)

        val sum = findMemorySumOfBitProgram(bitProgram)
        assertEquals(12408060320841L,sum)
    }

    @Test
    fun `Floating Bitmask on 42 should return 26, 27, 58 and 59`(){
        val bitmask = "000000000000000000000000000000X1001X"
        val transformedList = applyFloatingBitmask(42,bitmask)
        assertAll(
                {assertEquals(26,transformedList[0])},
                {assertEquals(27,transformedList[1])},
                {assertEquals(58,transformedList[2])},
                {assertEquals(59,transformedList[3])}
        )
    }

    @Test
    @Throws(Exception::class)
    fun `Small Floating Program Should Have Sum of 165`(){
        val path = Paths.get("src/test/kotlin/day_14/floating_bit_program.txt")
        val bitProgram = PuzzleInputReader.readStringListFromFile(path)

        val sum = findMemorySumOfFloatingProgram(bitProgram)
        assertEquals(208,sum)
    }

    @Test
    @Tag("slow")
    @Throws(Exception::class)
    fun `Puzzle Floating Bit Program Test`(){
        val path = Paths.get("src/test/kotlin/day_14/puzzle_bit_program.txt")
        val bitProgram = PuzzleInputReader.readStringListFromFile(path)

        val sum = findMemorySumOfFloatingProgram(bitProgram)
        assertEquals(4466434626828,sum)
    }
}