package day_11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import util.PuzzleInputReader
import java.nio.file.Paths

class SeatingSystemTests {
    @Test
    @Throws(Exception::class)
    fun `Load Initial Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val seatList = PuzzleInputReader.readStringListFromFile(path)

        val seatingLayout = SeatingLayout.loadSeatingFromString(seatList)
        assertAll("Check initial seat counts",
                {assertEquals(71,seatingLayout.getSeatCount(Seat.EMPTY))},
                {assertEquals(29,seatingLayout.getSeatCount(Seat.FLOOR))}
        )
    }

    @Test
    @Throws(Exception::class)
    fun `Solve for Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val joltageList = PuzzleInputReader.readStringListFromFile(path)
        assertEquals(0,1); // Failing until implemented
    }
}