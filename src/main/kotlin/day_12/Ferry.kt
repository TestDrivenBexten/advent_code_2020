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
    private var xFerryCoordinate = 0
    private var yFerryCoordinate = 0

    private var xWaypointCoordinate = 10
    private var yWaypointCoordinate = 1

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
                    yFerryCoordinate += command.magnitude
                }
                CommandType.South -> {
                    yFerryCoordinate -= command.magnitude
                }
                CommandType.West -> {
                    xFerryCoordinate -= command.magnitude
                }
                CommandType.East -> {
                    xFerryCoordinate += command.magnitude
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

    fun executeWaypointCommands(){
        ferryCommandList.map { command ->
            when(command.commandType){
                CommandType.North -> {
                    yWaypointCoordinate += command.magnitude
                }
                CommandType.South -> {
                    yWaypointCoordinate -= command.magnitude
                }
                CommandType.West -> {
                   xWaypointCoordinate -= command.magnitude
                }
                CommandType.East -> {
                    xWaypointCoordinate += command.magnitude
                }
                CommandType.Right -> {
                    rotateWaypointRight(command.magnitude)
                }
                CommandType.Left -> {
                    rotateWaypointLeft(command.magnitude)
                }
                CommandType.Forward -> moveWaypointForward(command.magnitude)
            }
        }
    }

    fun getManhattanDistance(): Int{
        return abs(xFerryCoordinate) + abs(yFerryCoordinate)
    }

    private fun moveShipForward(magnitude: Int){
        when(shipDirection){
            0 -> xFerryCoordinate += magnitude
            90 -> yFerryCoordinate -= magnitude
            180 -> xFerryCoordinate -= magnitude
            270 -> yFerryCoordinate += magnitude
        }
    }

    private fun rotateShipLeft(degree: Int){
        shipDirection -= degree
        shipDirection = correctedDegree(shipDirection)
    }

    private fun rotateShipRight(degree: Int){
        shipDirection += degree
        shipDirection = correctedDegree(shipDirection)
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

    private fun rotateWaypointRight(degree: Int){
        val xDiff = xWaypointCoordinate - xFerryCoordinate
        val yDiff = yWaypointCoordinate - yFerryCoordinate

        when(correctedDegree(degree)){
            90 -> {
                xWaypointCoordinate = xFerryCoordinate + yDiff
                yWaypointCoordinate = yFerryCoordinate - xDiff
            }
            180 -> {
                xWaypointCoordinate = xFerryCoordinate - xDiff
                yWaypointCoordinate = yFerryCoordinate - yDiff
            }
            270 -> {
                xWaypointCoordinate = xFerryCoordinate - yDiff
                yWaypointCoordinate = yFerryCoordinate + xDiff
            }
        }
    }

    private fun rotateWaypointLeft(degree: Int){
        val xDiff = xWaypointCoordinate - xFerryCoordinate
        val yDiff = yWaypointCoordinate - yFerryCoordinate

        when(correctedDegree(degree)){
            90 -> {
                xWaypointCoordinate = xFerryCoordinate - yDiff
                yWaypointCoordinate = yFerryCoordinate + xDiff
            }
            180 -> {
                xWaypointCoordinate = xFerryCoordinate - xDiff
                yWaypointCoordinate = yFerryCoordinate - yDiff
            }
            270 -> {
                xWaypointCoordinate = xFerryCoordinate + yDiff
                yWaypointCoordinate = yFerryCoordinate - xDiff
            }
        }
    }

    private fun correctedDegree(degree: Int): Int {
        var correctedDegree = degree
        correctedDegree %= 360
        if(correctedDegree == -90){
            correctedDegree = 270
        }else if(correctedDegree == -270){
            correctedDegree = 90
        }
        correctedDegree = abs(correctedDegree)
        return correctedDegree
    }

    private fun moveWaypointForward(magnitude: Int){
        val xDiff = xWaypointCoordinate - xFerryCoordinate
        val yDiff = yWaypointCoordinate - yFerryCoordinate

        val xMove = xDiff * magnitude
        val yMove = yDiff * magnitude

        xFerryCoordinate += xMove
        xWaypointCoordinate += xMove
        yFerryCoordinate += yMove
        yWaypointCoordinate += yMove
    }
}