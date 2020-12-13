package day_12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class FerryTests {
    @Test
    @Throws(Exception::class)
    fun `Small Ferry Command List Should Have Manhattan Distance 25`(){
        val path = Paths.get("src/test/kotlin/day_12/sample_command.txt")
        val commandList = PuzzleInputReader.readStringListFromFile(path)

        val ferry = Ferry(commandList)
        assertEquals(25,ferry.getManhattanDistance())
    }
}