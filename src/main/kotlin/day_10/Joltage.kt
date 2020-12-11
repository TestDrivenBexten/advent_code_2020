package day_10

data class JoltageDifference(val oneJoltCount: Int, val threeJoltCount: Int)

fun getJoltageDifference(joltageList: List<Int>): JoltageDifference{
    val sortedList = buildJoltageList(joltageList)

    var oneJoltCount = 0
    var threeJoltCount = 0
    for (j in 0 until sortedList.size - 1){
        val currentJoltage = sortedList[j]
        val nextJoltage = sortedList[j + 1]
        if(nextJoltage - currentJoltage == 1) {
            oneJoltCount++
        }else{
            threeJoltCount++
        }
    }
    return JoltageDifference(oneJoltCount,threeJoltCount)
}

fun getArrangementCount(joltageList: List<Int>): Int {
    val sortedList = buildJoltageList(joltageList)
    val arrangementList = buildArrangementList(sortedList)
    println(arrangementList)
    return arrangementList.size
}

private fun buildArrangementList(joltageList: List<Int>): List<List<Int>>{
    val currentJoltage = joltageList[0]

    val indexOneGreater = joltageList.indexOf(currentJoltage + 1)
    val indexThreeGreater = joltageList.indexOf(currentJoltage + 3)

    var oneArrangementList: List<List<Int>> = listOf()
    if (indexOneGreater != -1){
        val oneSublist = joltageList.subList(indexOneGreater,joltageList.size)
        oneArrangementList = buildArrangementList(oneSublist)
    }

    var threeArrangementList: List<List<Int>> = listOf()
    if(indexThreeGreater != -1){
        val threeSublist = joltageList.subList(indexThreeGreater,joltageList.size)
        threeArrangementList = buildArrangementList(threeSublist)
    }

    oneArrangementList = oneArrangementList.map {
        arrangementList ->
            val mutArrangementList = arrangementList.toMutableList()
            mutArrangementList.add(0,currentJoltage)
            mutArrangementList
    }
    if (oneArrangementList.isEmpty()){
        oneArrangementList = listOf(listOf(currentJoltage))
    }

    threeArrangementList = threeArrangementList.map {
        arrangementList ->
        val mutArrangementList = arrangementList.toMutableList()
        mutArrangementList.add(0,currentJoltage)
        mutArrangementList
    }
    if (threeArrangementList.isEmpty()){
        threeArrangementList = listOf(listOf(currentJoltage))
    }

    print("One sublist: ")
    println(oneArrangementList)
    print("Three sublist: ")
    println(threeArrangementList)
    print("Combined: ")
    println(oneArrangementList + threeArrangementList)

    return oneArrangementList + threeArrangementList
}

private fun buildJoltageList(joltageList: List<Int>): List<Int>{
    val sortedList = joltageList.sorted().toMutableList()
    sortedList.add(0,0)
    sortedList.add(sortedList.last() + 3)
    return sortedList
}