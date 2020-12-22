package day_17

import kotlin.math.abs

data class Cube(val x: Int, val y: Int, val z: Int, val active: Boolean)
class CubeGrid(private var cubeList: List<Cube>) {
    fun advanceCycle(){
        expandCube()
    }

    fun getActiveCubeCount(): Int{
        return cubeList.count { it.active }
    }

    fun expandCube() {
        val minX = cubeList.minOf { it.x }
        val maxX = cubeList.maxOf { it.x }

        val minY = cubeList.minOf { it.y }
        val maxY = cubeList.maxOf { it.y }

        val minZ = cubeList.minOf { it.z }
        val maxZ = cubeList.maxOf { it.z }

        val emptyCubeList = (minX - 1..maxX + 1).flatMap { x ->
            (minY - 1..maxY + 1).flatMap { y ->
                (minZ - 1..maxZ + 1).map { z ->
                    Cube(x,y,z,false)
                }
            }
        }

        // TODO Replace inactive cubes with active cubes
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
    if (abs(cube1.x - cube2.x) > 1) return false
    if (abs(cube1.y - cube2.y) > 1) return false
    if (abs(cube1.z - cube2.z) > 1) return false
    return true
}