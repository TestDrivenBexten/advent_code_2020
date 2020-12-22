package day_17

data class Cube(val x: Int, val y: Int, val z: Int, val active: Boolean)
class CubeGrid(private val cubeList: List<Cube>) {
    fun advanceCycle(){
        // TODO
    }

    fun getActiveCubeCount(): Int{
        return cubeList.count { it.active }
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
    return false
}