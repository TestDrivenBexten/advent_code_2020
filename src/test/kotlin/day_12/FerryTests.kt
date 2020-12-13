package day_12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class FerryTests {
    @Test
    @Throws(Exception::class)
    fun `Load Initial Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_12/sample_command.txt")
        val commandList = PuzzleInputReader.readStringListFromFile(path)

        val ferry = Ferry(commandList)
        assertEquals(25,ferry.getManhattanDistance())
    }
}