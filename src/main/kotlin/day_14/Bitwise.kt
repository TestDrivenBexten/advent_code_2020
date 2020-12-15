package day_14

import java.util.regex.Pattern
import kotlin.math.pow

data class MemoryAddress(val address: Int, var storedValue: Int)

fun findMemorySumOfBitProgram(instructionList: List<String>): Int {
    val memoryList = mutableListOf<MemoryAddress>()
    val bitmask = instructionList.first().substring(7)

    val memRegex = "mem\\[(\\d+)\\]\\s=\\s(\\d+)"
    val r = Pattern.compile(memRegex)

    instructionList.drop(1).map {
        val m = r.matcher(it)
        if(m.matches()){
            val memoryAddress = Integer.parseInt(m.group(1))
            val rawValue = Integer.parseInt(m.group(2))
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

fun applyBitmask(originalValue: Int, bitmask: String): Int {
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

private fun convertToDecimal(binary: String): Int {
    var decimalValue = 0
    val reversedBinary = binary.reversed()
    for(index in reversedBinary.indices){
        val bit = Integer.parseInt(reversedBinary[index].toString())
        if(bit == 1){
            decimalValue += 2.0.pow(index).toInt()
        }
    }
    return decimalValue
}