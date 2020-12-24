package day_17

data class Hypercube(val x: Int, val y: Int, val z: Int, val w: Int, val active: Boolean)
class HypercubeGrid(private var cubeList: List<Hypercube>) {
    fun advanceCycle(){

    }

    fun getActiveCubeCount(): Int{
        return cubeList.count { it.active }
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