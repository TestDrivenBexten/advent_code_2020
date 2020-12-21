package day_16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import util.PuzzleInputReader
import java.nio.file.Paths

class TicketTests {

    @Test
    fun `Small Ticket Config Should Have Error Rate of 71`(){
        val path = Paths.get("src/test/kotlin/day_16/small_ticket.txt")
        val rawConfig = PuzzleInputReader.readStringListFromFile(path)

        val ticketConfig = loadTicketConfig(rawConfig)
        val scanErrorRate = getTicketErrorRate(ticketConfig)

        assertEquals(71,scanErrorRate)
    }

    @Test
    fun `Small Config Should Have Correct Ordering of Fields`(){
        val path = Paths.get("src/test/kotlin/day_16/small_ordering.txt")
        val rawConfig = PuzzleInputReader.readStringListFromFile(path)

        val ticketConfig = loadTicketConfig(rawConfig)
        val fieldOrderMap = getTicketFieldOrder(ticketConfig)
        assertAll(
            { assertEquals(1,fieldOrderMap.get("row")) },
            { assertEquals(2,fieldOrderMap.get("class")) },
            { assertEquals(3,fieldOrderMap.get("seat")) },
        )
    }

    @Test
    fun `Puzzle Ticket Config Error Rate`(){
        val path = Paths.get("src/test/kotlin/day_16/puzzle_ticket.txt")
        val rawConfig = PuzzleInputReader.readStringListFromFile(path)

        val ticketConfig = loadTicketConfig(rawConfig)
        val scanErrorRate = getTicketErrorRate(ticketConfig)

        assertEquals(28882,scanErrorRate)
    }

    @Test
    fun `Puzzle Ticket Config Departure Product`(){
        val path = Paths.get("src/test/kotlin/day_16/puzzle_ticket.txt")
        val rawConfig = PuzzleInputReader.readStringListFromFile(path)

        val ticketConfig = loadTicketConfig(rawConfig)
        val departureProduct = getDepartureProduct(ticketConfig)
        assertEquals(1429779530273L,departureProduct)
    }

    @Test
    @Tag("skipped") // May implement later
    fun `Combine Ticket Fields Ranges`(){
        val rangeList = listOf(
            FieldRange(1,3),
            FieldRange(5,7),
            FieldRange(6,11),
            FieldRange(33,44),
            FieldRange(13,40),
            FieldRange(45,50)
        )
        val combinedRangeList = combineTicketRanges(rangeList)
        assertAll(
            { assertEquals(FieldRange(1,3), combinedRangeList[0]) },
            { assertEquals(FieldRange(5,11), combinedRangeList[1]) },
            { assertEquals(FieldRange(13,50), combinedRangeList[2]) }
        )
    }
}