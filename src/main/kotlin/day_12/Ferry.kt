package day_12

class Ferry(commandList: List<String>) {
    var xCoordinate = 0
    var yCoordinate = 0

    init {
        println(commandList)
    }

    fun getManhattanDistance(): Int{
        return xCoordinate + yCoordinate
    }
}