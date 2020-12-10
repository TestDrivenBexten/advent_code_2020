package day_09

fun findNonMatchingNumberFromList(preambleLength: Int, numberList: List<Long>): Long{
    var preambleList: List<Long>

    var startOffset = 0
    while(startOffset + preambleLength + 1 < numberList.size){
        preambleList = numberList.subList(startOffset,preambleLength + startOffset)

        var hasSumMatch = false
        val nextNumber = numberList[startOffset + preambleLength + 1]
        for(j in 0 until preambleLength - 1){
            for(k in j + 1 until preambleLength){
                val firstNum = preambleList[j]
                val secondNum = preambleList[k]
                if(firstNum + secondNum == nextNumber){
                    hasSumMatch = true
                }
            }
        }
        if (!hasSumMatch) {
            return nextNumber
        }
        startOffset++
    }
    return 0
}