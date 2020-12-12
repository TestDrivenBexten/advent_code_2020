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

        val seatLayoutList = loadSeatingFromString(seatList)
        val emptySeatCount = seatLayoutList.fold(0) { sum, seatingRow ->
            sum + seatingRow.fold(0) {emptyCount, seat ->
                if (seat == Seat.EMPTY){
                    emptyCount + 1
                } else {
                    emptyCount
                }
            }
        }
        val floorCount = seatLayoutList.fold(0) { sum, seatingRow ->
            sum + seatingRow.fold(0) {emptyCount, seat ->
                if (seat == Seat.FLOOR){
                    emptyCount + 1
                } else {
                    emptyCount
                }
            }
        }
        
        assertAll("",
                {assertEquals(71,emptySeatCount)},
                {assertEquals(29,floorCount)}
        )
        assertEquals(0,1); // Failing until implemented
    }

    @Test
    @Throws(Exception::class)
    fun `Solve for Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_11/sample_1_initial.txt")
        val joltageList = PuzzleInputReader.readStringListFromFile(path)
        assertEquals(0,1); // Failing until implemented
    }
}