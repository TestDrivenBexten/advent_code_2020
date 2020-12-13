package day_12

import java.util.regex.Pattern
import kotlin.math.abs

enum class CommandType {
    North, South, West, East, Forward, Right, Left
}

data class Command(val commandType: CommandType, val magnitude: Int)

class Ferry(commandList: List<String>) {
    private val ferryCommandList: List<Command>
    private var shipDirection = 0
    var xCoordinate = 0
    var yCoordinate = 0

    private val commandRegex = "([A-Z])(\\d+)"

    init {
        val r = Pattern.compile(commandRegex)
        ferryCommandList = commandList.map { command ->
            val m = r.matcher(command)
            if(m.matches()){
                val commandChar = m.group(1)
                val numeric = m.group(2).toInt()
                when(commandChar){
                    "N" -> Command(CommandType.North,numeric)
                    "S" -> Command(CommandType.South,numeric)
                    "W" -> Command(CommandType.West,numeric)
                    "E" -> Command(CommandType.East,numeric)
                    "F" -> Command(CommandType.Forward,numeric)
                    "R" -> Command(CommandType.Right,numeric)
                    "L" -> Command(CommandType.Left,numeric)
                    else -> Command(CommandType.Forward,0)
                }
            } else {
                Command(CommandType.Forward,0)
            }
        }
    }

    fun executeCommands(){
        ferryCommandList.map { command ->
            when(command.commandType){
                CommandType.North -> {
                    yCoordinate += command.magnitude
                }
                CommandType.South -> {
                    yCoordinate -= command.magnitude
                }
                CommandType.West -> {
                    xCoordinate -= command.magnitude
                }
                CommandType.East -> {
                    xCoordinate += command.magnitude
                }
                CommandType.Right -> {
                    rotateShipRight(command.magnitude)
                }
                CommandType.Left -> {
                    rotateShipLeft(command.magnitude)
                }
                CommandType.Forward -> moveShipForward(command.magnitude)
            }
        }
    }

    fun getManhattanDistance(): Int{
        return abs(xCoordinate) + abs(yCoordinate)
    }

    private fun moveShipForward(magnitude: Int){
        when(shipDirection){
            0 -> xCoordinate += magnitude
            90 -> yCoordinate -= magnitude
            180 -> xCoordinate -= magnitude
            270 -> yCoordinate += magnitude
        }
    }

    private fun rotateShipLeft(degree: Int){
        shipDirection -= degree
        correctShipDirection()
    }

    private fun rotateShipRight(degree: Int){
        shipDirection += degree
        correctShipDirection()
    }

    private fun correctShipDirection(){
        shipDirection %= 360
        if(shipDirection == -90){
            shipDirection = 270
        }else if(shipDirection == -270){
            shipDirection = 90
        }
        shipDirection = abs(shipDirection)
    }
}