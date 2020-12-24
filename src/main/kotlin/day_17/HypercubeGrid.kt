package day_17

import kotlin.math.abs

data class Hypercube(val x: Int, val y: Int, val z: Int, val w: Int, val active: Boolean)
class HypercubeGrid(private var cubeList: List<Hypercube>) {
    fun advanceCycle(){
        expandCube()
        val nextCubeList = cubeList.map {
            val neighborCount = getActiveNeighborCount(it)
            if(it.active){
                if(neighborCount == 2 || neighborCount == 3){
                    it
                } else {
                    Hypercube(it.x,it.y,it.z,it.w,false)
                }
            } else {
                if(neighborCount == 3){
                    Hypercube(it.x,it.y,it.z,it.w,true)
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

    private fun getActiveNeighborCount(cube: Hypercube): Int{
        return cubeList.count { isNeighborToHypercube(cube,it) && it.active }
    }

    private fun expandCube() {
        val minX = cubeList.minOf { it.x }
        val maxX = cubeList.maxOf { it.x }

        val minY = cubeList.minOf { it.y }
        val maxY = cubeList.maxOf { it.y }

        val minZ = cubeList.minOf { it.z }
        val maxZ = cubeList.maxOf { it.z }

        val minW = cubeList.minOf { it.w }
        val maxW = cubeList.maxOf { it.w }

        val emptyCubeList = (minX - 1..maxX + 1).flatMap { x ->
            (minY - 1..maxY + 1).flatMap { y ->
                (minZ - 1..maxZ + 1).flatMap { z ->
                    (minW - 1..maxW + 1).map { w ->
                        val isActiveCube = cubeList.count { it.x == x && it.y == y && it.z == z &&
                                it.w == w && it.active } == 1
                        Hypercube(x,y,z,w,isActiveCube)
                    }
                }
            }
        }

        cubeList = emptyCubeList
    }
}

fun loadHypercubeGridFromLayout(layout: List<String>): HypercubeGrid{
    val cubeList = layout.flatMapIndexed { rowIndex, layoutRow ->
        layoutRow.mapIndexed { colIndex, cubeChar ->
            if(cubeChar == '#'){
                Hypercube(colIndex,rowIndex,0,0,true)
            } else {
                Hypercube(colIndex,rowIndex,0,0,false)
            }
        }
    }
    return HypercubeGrid(cubeList)
}

fun isNeighborToHypercube(cube1: Hypercube, cube2: Hypercube): Boolean {
    if(cube1.x == cube2.x && cube1.y == cube2.y &&
            cube1.z == cube2.z && cube1.w == cube2.w) {
        return false
    }
    if (abs(cube1.x - cube2.x) > 1) return false
    if (abs(cube1.y - cube2.y) > 1) return false
    if (abs(cube1.z - cube2.z) > 1) return false
    if (abs(cube1.w - cube2.w) > 1) return false
    return true
}