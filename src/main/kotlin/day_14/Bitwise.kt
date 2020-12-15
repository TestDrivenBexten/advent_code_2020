package day_14

import kotlin.math.pow

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