package day_15

fun reciteMemory(stringInput: String, iterationCount: Int): Int{
    val startingNumberList = stringInput.split(",").map { Integer.parseInt(it) }
    var currentTurn = 1

    val numberRecordMap = HashMap<Int,Int>()
    for(index in startingNumberList.indices){
        val startingNumber = startingNumberList[index]
        numberRecordMap[startingNumber] = index + 1
        currentTurn++
    }

    var numberSpoken = 0
    currentTurn++
    while(currentTurn <= iterationCount){
        if(numberRecordMap.containsKey(numberSpoken)){
            val lastTurn = numberRecordMap[numberSpoken]
            val previousTurn = currentTurn - 1
            val turnDiff = previousTurn - lastTurn!!
            numberRecordMap[numberSpoken] = previousTurn
            numberSpoken = turnDiff
        } else {
            numberRecordMap[numberSpoken] = currentTurn
            numberSpoken = 0
        }
        currentTurn++
    }

    return numberSpoken
}