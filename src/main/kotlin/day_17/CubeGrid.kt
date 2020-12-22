package day_17

data class Cube(val x: Int, val y: Int, val z: Int)
class CubeGrid(val cubeMap: Map<Long,Cube>) {
    fun getActiveCubeCount(): Int{
        return 0
    }
}

fun loadCubeGridFromLayout(layout: List<String>): CubeGrid{
    val cubeMap = mapOf<Long,Cube>()

    return CubeGrid(mapOf())
}