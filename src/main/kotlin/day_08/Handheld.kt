package day_08

import java.util.regex.Pattern

class Handheld {
    private var accumulatorValue = 0
    private var commandList = mutableListOf<HandheldCommand>()
    private var currentInstruction = 0;
    private val visitedInstructionList = mutableListOf<HandheldCommand>()

    private var willTerminate = false

    private val commandRegex = "(\\w+) ([-+]\\d+)"

    fun loadProgram(instructionList: List<String>){
        val r = Pattern.compile(commandRegex)
        commandList.clear()
        instructionList.map { instruction ->
            val m = r.matcher(instruction)
            if(!m.matches()){
                return
            }

            val command = m.group(1)
            val numeric = m.group(2).toInt()
            val handheldCommand = buildHandheldCommand(command,numeric)
            commandList.add(handheldCommand)
        }
    }

    fun runProgram(){
        currentInstruction = 0
        visitedInstructionList.clear()
        var command = commandList[currentInstruction]

        while(visitedInstructionList.indexOf(command) == -1) {
            when (command) {
                is AccCommand -> {
                    currentInstruction++
                    accumulatorValue += command.commandValue
                }
                is JumpCommand -> currentInstruction += command.commandValue
                is NoopCommand -> currentInstruction++
                else -> println("Command not recognized")
            }
            visitedInstructionList.add(command)
            if(currentInstruction >= commandList.size){
                willTerminate = true
            } else {
                command = commandList[currentInstruction]
            }
        }
    }

    fun willProgramTerminate():Boolean{
        runProgram()
        return willTerminate
    }

    fun getAccumulatedValue(): Int{
        return accumulatorValue
    }

    private fun buildHandheldCommand(command: String, numeric: Int): HandheldCommand{
        return when(command){
            "acc" -> AccCommand(numeric)
            "jmp" -> JumpCommand(numeric)
            "nop" -> NoopCommand(numeric)
            else -> NoopCommand(0)
        }
    }
}

interface HandheldCommand {
    val commandValue: Int
    fun execute(): Int
}

class JumpCommand(addressValue: Int): HandheldCommand {
    override val commandValue: Int = addressValue
    override fun execute(): Int {
        return commandValue
    }
}

class NoopCommand(zeroValue: Int): HandheldCommand {
    override val commandValue: Int = zeroValue
    override fun execute(): Int {
        return commandValue
    }
}

class AccCommand(accValue: Int): HandheldCommand {
    override val commandValue: Int = accValue
    override fun execute(): Int {
        return commandValue
    }
}