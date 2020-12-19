package day_14

import java.util.regex.Pattern
import kotlin.math.pow

data class MemoryAddress(val address: Int, var storedValue: Long)

fun findMemorySumOfFloatingProgram(instructionList: List<String>): Long {
    return 0L
}

fun findMemorySumOfBitProgram(instructionList: List<String>): Long {
    val memoryList = mutableListOf<MemoryAddress>()

    val maskRegex = "mask = (\\w+)"
    val rMask = Pattern.compile(maskRegex)

    val memRegex = "mem\\[(\\d+)\\]\\s=\\s(\\d+)"
    val r = Pattern.compile(memRegex)

    var bitmask = ""
    for(index in instructionList.indices){
        val instruction = instructionList[index]
        val maskMatcher = rMask.matcher(instruction)
        val memoryMatcher = r.matcher(instruction)

        if(maskMatcher.matches()){
            bitmask = maskMatcher.group(1)
        }else if(memoryMatcher.matches()){
            val memoryAddress = Integer.parseInt(memoryMatcher.group(1))
            val rawValue = Integer.parseInt(memoryMatcher.group(2))
            val maskedValue = applyBitmask(rawValue,bitmask)

            val storedMemory = memoryList.find { x ->
                x.address == memoryAddress
            }
            if(storedMemory == null){
                val memory = MemoryAddress(memoryAddress, maskedValue)
                memoryList.add(memory)
            }else{
                storedMemory.storedValue = maskedValue
            }
        }
    }

    return memoryList.fold(0) { sum, memory -> sum + memory.storedValue }
}

fun applyBitmask(originalValue: Int, bitmask: String): Long {
    var binary = convertToBinary(originalValue)
    for(index in bitmask.indices){
        val bitChar = bitmask[index]
        if(bitChar != 'X'){
            binary = binary.substring(0,index) + bitChar + binary.substring(index + 1)
        }
    }
    return convertToDecimal(binary)
}

private fun convertToBinary(decimal: Int): String {
    val binary = Integer.toBinaryString(decimal)
    return binary.padStart(36,'0')
}

private fun convertToDecimal(binary: String): Long {
    var decimalValue = 0L
    val reversedBinary = binary.reversed()
    for(index in reversedBinary.indices){
        val bit = Integer.parseInt(reversedBinary[index].toString())
        if(bit == 1){
            decimalValue += 2.0.pow(index).toLong()
        }
    }
    return decimalValue
}