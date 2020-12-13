package day_11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
    fun `Load Initial Sample Layout After 1 Round`(){
        val path = Paths.get("src/test/kotlin/day_11/sample_1_round_1.txt")
        val seatList = PuzzleInputReader.readStringListFromFile(path)

        val seatingLayout = SeatingLayout.loadSeatingFromString(seatList)
        assertAll("Check initial seat counts",
                {assertEquals(71,seatingLayout.getSeatCount(Seat.OCCUPIED))},
                {assertEquals(29,seatingLayout.getSeatCount(Seat.FLOOR))}
        )
    }

    @Test
    @Throws(Exception::class)
    fun `Load Initial Sample And Verify One Round`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val initialSeatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList)
        initialSeatingLayout.advanceRound()

        val firstRoundPath = Paths.get("src/test/kotlin/day_11/sample_1_round_1.txt")
        val firstRoundSeatList = PuzzleInputReader.readStringListFromFile(firstRoundPath)
        val firstRoundSeatingLayout = SeatingLayout.loadSeatingFromString(firstRoundSeatList)

        assertTrue(initialSeatingLayout.hasSameLayout(firstRoundSeatingLayout))
    }

    @Test
    @Throws(Exception::class)
    fun `Load Initial Sample And Verify Two Rounds`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val initialSeatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList)
        initialSeatingLayout.advanceRound()
        initialSeatingLayout.advanceRound()

        val secondRoundPath = Paths.get("src/test/kotlin/day_11/sample_1_round_2.txt")
        val secondRoundSeatList = PuzzleInputReader.readStringListFromFile(secondRoundPath)
        val secondRoundSeatingLayout = SeatingLayout.loadSeatingFromString(secondRoundSeatList)

        assertTrue(initialSeatingLayout.hasSameLayout(secondRoundSeatingLayout))
    }

    @Test
    @Throws(Exception::class)
    fun `Solve for Sample Layout`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val seatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList)
        seatingLayout.autoAdvance()

        assertEquals(37,seatingLayout.getSeatCount(Seat.OCCUPIED))
    }

    @Test
    @Throws(Exception::class)
    fun `Count Final Occupied Seats for Puzzle Input`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/puzzle_seating_input.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val seatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList)
        seatingLayout.autoAdvance()

        assertEquals(2108,seatingLayout.getSeatCount(Seat.OCCUPIED))
    }

    @Test
    @Throws(Exception::class)
    fun `Solve for Complicated Layout`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val seatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList, true)
        seatingLayout.autoAdvance()

        assertEquals(26,seatingLayout.getSeatCount(Seat.OCCUPIED))
    }

    @Test
    @Throws(Exception::class)
    fun `Count Final Occupied Seats for Complicated Puzzle Input`(){
        val initialPath = Paths.get("src/test/kotlin/day_11/puzzle_seating_input.txt")
        val initialSeatList = PuzzleInputReader.readStringListFromFile(initialPath)

        val seatingLayout = SeatingLayout.loadSeatingFromString(initialSeatList, true)
        seatingLayout.autoAdvance()

        assertEquals(1897,seatingLayout.getSeatCount(Seat.OCCUPIED))
    }
}