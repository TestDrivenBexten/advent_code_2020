package day_09

fun findNonMatchingNumberFromList(preambleLength: Int, numberList: List<Long>): Long{
    var preambleList: List<Long>

    var startOffset = 0
    while(startOffset + preambleLength + 1 < numberList.size){
        preambleList = numberList.subList(startOffset,preambleLength + startOffset)

        var hasSumMatch = false
        val nextNumber = numberList[startOffset + preambleLength]
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

fun findEncryptionWeakness(preambleLength: Int, numberList: List<Long>): Long {
    val nonMatchingNumber = findNonMatchingNumberFromList(preambleLength, numberList)
    val listLength = numberList.size
    for(j in 0..listLength){
        for(k in j..listLength){
            val rangeList = numberList.subList(j,k)
            if(nonMatchingNumber == rangeList.sum()){
                return rangeList.min()?.plus(rangeList.max()!!)!!
            }
        }
    }
    return 0
}