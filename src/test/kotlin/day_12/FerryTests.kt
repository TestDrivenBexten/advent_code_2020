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
        ferry.executeCommands()
        assertEquals(25,ferry.getManhattanDistance())
    }

    @Test
    @Throws(Exception::class)
    fun `Find Manhattan Distance for Puzzle Input`(){
        val path = Paths.get("src/test/kotlin/day_12/puzzle_command.txt")
        val commandList = PuzzleInputReader.readStringListFromFile(path)

        val ferry = Ferry(commandList)
        ferry.executeCommands()
        assertEquals(998,ferry.getManhattanDistance())
    }

    @Test
    @Throws(Exception::class)
    fun `Small Waypoint Command List Should Have Manhattan Distance 286`(){
        val path = Paths.get("src/test/kotlin/day_12/sample_command.txt")
        val commandList = PuzzleInputReader.readStringListFromFile(path)

        val ferry = Ferry(commandList)
        ferry.executeWaypointCommands()
        assertEquals(286,ferry.getManhattanDistance())
    }

    @Test
    @Throws(Exception::class)
    fun `Find Manhattan Distance for Waypoint Puzzle Input`(){
        val path = Paths.get("src/test/kotlin/day_12/puzzle_command.txt")
        val commandList = PuzzleInputReader.readStringListFromFile(path)

        val ferry = Ferry(commandList)
        ferry.executeWaypointCommands()
        assertEquals(71586,ferry.getManhattanDistance())
    }
}