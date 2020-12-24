package day_17

import kotlin.math.abs

data class Cube(val x: Int, val y: Int, val z: Int, val active: Boolean)
class CubeGrid(private var cubeList: List<Cube>) {
    fun advanceCycle(){
        expandCube()
        val nextCubeList = cubeList.map {
            val neighborCount = getActiveNeighborCount(it)
            if(it.active){
                if(neighborCount == 2 || neighborCount == 3){
                    it
                } else {
                    Cube(it.x,it.y,it.z,false)
                }
            } else {
                if(neighborCount == 3){
                    Cube(it.x,it.y,it.z,true)
                } else {
                    it
                }
            }
        }
        cubeList = nextCubeList
    }

    fun getActiveCubeCount(): Int{
        return cubeList.count { it.active }
    }

    fun displayLayers() {
        val minZ = cubeList.minOf { it.z }
        val maxZ = cubeList.maxOf { it.z }
        (minZ..maxZ).map { z ->
            println("Z: $z")
            val minY = cubeList.minOf { it.y }
            val maxY = cubeList.maxOf { it.y }
            (minY..maxY).map { y ->
                val minX = cubeList.minOf { it.x }
                val maxX = cubeList.maxOf { it.x }
                (minX..maxX).map { x ->
                    val isActive = cubeList.count { it.x == x && it.y == y && it.z == z && it.active } == 1
                    if(isActive){
                        print("#")
                    } else {
                        print(".")
                    }
                }
                println()
            }
        }
    }

    private fun getActiveNeighborCount(cube: Cube): Int{
        return cubeList.count { isNeighborToCube(cube,it) && it.active }
    }

    private fun expandCube() {
        val minX = cubeList.minOf { it.x }
        val maxX = cubeList.maxOf { it.x }

        val minY = cubeList.minOf { it.y }
        val maxY = cubeList.maxOf { it.y }

        val minZ = cubeList.minOf { it.z }
        val maxZ = cubeList.maxOf { it.z }

        val emptyCubeList = (minX - 1..maxX + 1).flatMap { x ->
            (minY - 1..maxY + 1).flatMap { y ->
                (minZ - 1..maxZ + 1).map { z ->
                    val isActiveCube = cubeList.count { it.x == x && it.y == y && it.z == z && it.active } == 1
                    Cube(x,y,z,isActiveCube)
                }
            }
        }

        cubeList = emptyCubeList
    }
}

fun loadCubeGridFromLayout(layout: List<String>): CubeGrid{
    val cubeList = layout.flatMapIndexed { rowIndex, layoutRow ->
        layoutRow.mapIndexed { colIndex, cubeChar ->
            if(cubeChar == '#'){
                Cube(colIndex,rowIndex,0,true)
            } else {
                Cube(colIndex,rowIndex,0,false)
            }
        }
    }
    return CubeGrid(cubeList)
}

fun isNeighborToCube(cube1: Cube, cube2: Cube): Boolean {
    if(cube1.x == cube2.x && cube1.y == cube2.y &&
            cube1.z == cube2.z) {
        return false
    }
    if (abs(cube1.x - cube2.x) > 1) return false
    if (abs(cube1.y - cube2.y) > 1) return false
    if (abs(cube1.z - cube2.z) > 1) return false
    return true
}