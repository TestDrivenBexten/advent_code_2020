package day_17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.PuzzleInputReader
import java.nio.file.Paths

class CubeGridTests {

    @Test
    fun `Load Cube Grid from Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        assertEquals(5, cubeGrid.getActiveCubeCount())
    }
}