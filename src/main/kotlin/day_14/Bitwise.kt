package day_14

import java.util.regex.Pattern
import kotlin.math.pow

data class MemoryAddress(val address: Long, var storedValue: Long)

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
            val memoryAddress = Integer.parseInt(memoryMatcher.group(1))
            val rawValue = Integer.parseInt(memoryMatcher.group(2))
            val addressList = applyFloatingBitmask(memoryAddress,bitmask)

            addressList.map { writeToMemory(memoryList, it, rawValue.toLong()) }
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

            writeToMemory(memoryList,memoryAddress.toLong(),maskedValue)
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
        }else if(bitChar == 'X') {
            binary = replaceCharAtIndex(binary,index,bitChar)
        }
    }

    val binaryList = replaceXInString(binary)
    return binaryList.map { convertToDecimal(it) }
}

private fun replaceXInString(binary: String): List<String>{
    if(binary.contains('X')){
        val bitIndex = binary.indexOfFirst { it == 'X' }
        val zeroBinary = replaceCharAtIndex(binary,bitIndex,'0')
        val oneBinary = replaceCharAtIndex(binary,bitIndex,'1')

        val zeroList = replaceXInString(zeroBinary)
        val oneList = replaceXInString(oneBinary)

        return zeroList + oneList
    } else {
        return listOf(binary)
    }
}

private fun writeToMemory(memoryList: MutableList<MemoryAddress>,
                          memoryAddress: Long, maskedValue: Long){
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