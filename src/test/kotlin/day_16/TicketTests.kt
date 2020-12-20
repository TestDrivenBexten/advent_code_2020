package day_16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
}