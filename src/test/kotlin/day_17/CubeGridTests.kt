package day_17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import util.PuzzleInputReader
import java.nio.file.Paths
import java.util.stream.Stream

class CubeGridTests {

    @Test
    fun `Load Cube Grid from Sample Layout`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        assertEquals(5, cubeGrid.getActiveCubeCount())
    }

    @ParameterizedTest
    @MethodSource("cubeNeighborProvider")
    fun `Cube Neighbors for 0,0,0`(candidateCube: Cube, isNeighbor: Boolean){
        val currentCube = Cube(0, 0, 0, true)
        val neighborStatus = isNeighborToCube(currentCube, candidateCube)
        assertEquals(isNeighbor, neighborStatus)
    }

    companion object {
        @JvmStatic
        fun cubeNeighborProvider(): Stream<Arguments?>? {
            return Stream.of(
                arguments(Cube(-1,0,0, true), true),
                arguments(Cube(0,-1,0, true), true),
                arguments(Cube(1,-1,0, true), true),
                arguments(Cube(1,-2,0, true), false),
                arguments(Cube(1,-1,2, true), false),
            )
        }
    }

    @Test
    fun `Cube Grid Advanced One Cycle Should Have 11 Active Cubes`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        cubeGrid.advanceCycle()
        assertEquals(11, cubeGrid.getActiveCubeCount())
    }

    @Test
    fun `Cube Grid Advanced Two Cycles Should Have 21 Active Cubes`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        repeat(2) {
            cubeGrid.advanceCycle()
        }
        assertEquals(21, cubeGrid.getActiveCubeCount())
    }

    @Test
    fun `Cube Grid Advanced Three Cycles Should Have 38 Active Cubes`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        repeat(3) {
            cubeGrid.advanceCycle()
        }
        assertEquals(38, cubeGrid.getActiveCubeCount())
    }

    @Test
    fun `Cube Grid Advanced Six Cycles Should Have 112 Active Cubes`(){
        val path = Paths.get("src/test/kotlin/day_17/small_layout.txt")
        val smallLayout = PuzzleInputReader.readStringListFromFile(path)

        val cubeGrid = loadCubeGridFromLayout(smallLayout)
        repeat(6) {
            cubeGrid.advanceCycle()
        }
        assertEquals(112, cubeGrid.getActiveCubeCount())
    }
}