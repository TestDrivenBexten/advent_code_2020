package day_13

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import util.PuzzleInputReader
import java.nio.file.Paths

class BusScheduleTests {
    @Test
    @Throws(Exception::class)
    fun `Small Bus Schedule Test`(){
        val path = Paths.get("src/test/kotlin/day_13/small_schedule.txt")
        val busInput = PuzzleInputReader.readStringListFromFile(path)

        val currentTimestamp = busInput[0].toInt()
        val busIdInput = busInput[1]

        val busIdList = parseBusList(busIdInput)

        val (busId, departureTimestamp) = findNextBus(currentTimestamp,busIdList)

        val productTimeDiffId = busId * (departureTimestamp - currentTimestamp)
        assertAll("Check small schedule values",
            { assertEquals(listOf(7,13,19,31,59), busIdList) },
            { assertEquals(59, busId) },
            { assertEquals(944, departureTimestamp)},
            { assertEquals(295, productTimeDiffId)}
        )
    }

    @Test
    @Throws(Exception::class)
    fun `Puzzle Bus Schedule Test`(){
        val path = Paths.get("src/test/kotlin/day_13/puzzle_schedule.txt")
        val busInput = PuzzleInputReader.readStringListFromFile(path)

        val currentTimestamp = busInput[0].toInt()
        val busIdInput = busInput[1]

        val busIdList = parseBusList(busIdInput)

        val (busId, departureTimestamp) = findNextBus(currentTimestamp,busIdList)

        val productTimeDiffId = busId * (departureTimestamp - currentTimestamp)
        assertEquals(119, productTimeDiffId)
    }

    @Test
    @Throws(Exception::class)
    fun `Small Bus Schedule Time Test`(){
        val path = Paths.get("src/test/kotlin/day_13/small_schedule.txt")
        val busInput = PuzzleInputReader.readStringListFromFile(path)

        val busIdInput = busInput[1]

        val timeStamp = findSynchronizedTimestamp(busIdInput)
        assertEquals(1068781,timeStamp)
    }

    @ParameterizedTest
    @CsvSource(
        "'67,7,59,61', 754018",
        "'67,x,7,59,61',779210",
        "'67,7,x,59,61',1261476",
        "'1789,37,47,1889',1202161486"
    )
    fun `Additional Offset Tests`(busInput: String, expectedTimestamp: Long){
        val actualTimestamp = findSynchronizedTimestamp(busInput)
        assertEquals(expectedTimestamp,actualTimestamp)
    }

//    TODO Reduce algorithm runtime
//    @Test
//    @Throws(Exception::class)
//    fun `Puzzle Bus Schedule Time Test`(){
//        val path = Paths.get("src/test/kotlin/day_13/puzzle_schedule.txt")
//        val busInput = PuzzleInputReader.readStringListFromFile(path)
//
//        val busIdInput = busInput[1]
//
//        val timeStamp = findSynchronizedTimestamp(busIdInput)
//        assertEquals(1068781,timeStamp)
//    }
}