package day_14

import java.util.regex.Pattern
import kotlin.math.pow

data class MemoryAddress(val address: Int, var storedValue: Long)

const val maskRegex = "mask = (\\w+)"
const val memRegex = "mem\\[(\\d+)\\]\\s=\\s(\\d+)"

fun findMemorySumOfFloatingProgram(instructionList: List<String>): Long {
    val memoryList = mutableListOf<MemoryAddress>()

    val rMask = Pattern.compile(maskRegex)
    val r = Pattern.compile(memRegex)

    var bitmask = ""
    for(index in instructionList.indices){
        val instruction = instructionList[index]
        val maskMatcher = rMask.matcher(instruction)
        val memoryMatcher = r.matcher(instruction)

        if(maskMatcher.matches()){
            bitmask = maskMatcher.group(1)
        }else if(memoryMatcher.matches()){
            // TODO
        }
    }

    return memoryList.fold(0) { sum, memory -> sum + memory.storedValue }
}

fun findMemorySumOfBitProgram(instructionList: List<String>): Long {
    val memoryList = mutableListOf<MemoryAddress>()

    val rMask = Pattern.compile(maskRegex)
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
            binary = replaceCharAtIndex(binary,index,bitChar)
        }
    }
    return convertToDecimal(binary)
}

fun applyFloatingBitmask(originalValue: Int, bitmask: String): List<Long> {
    var binary = convertToBinary(originalValue)
    for(index in bitmask.indices){
        val bitChar = bitmask[index]
        if(bitChar == '1'){
            binary = replaceCharAtIndex(binary,index,bitChar)
        }

        if(bitChar == 'X'){
            val zeroBinary = replaceCharAtIndex(binary,index,'0')
            val oneBinary = replaceCharAtIndex(binary,index,'1')
        }
    }
//    return convertToDecimal(binary)
    return listOf()
}

private fun replaceCharAtIndex(source: String, index: Int, character: Char): String{
    return source.substring(0,index) + character + source.substring(index + 1)
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