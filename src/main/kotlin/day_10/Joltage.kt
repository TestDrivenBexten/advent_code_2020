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

fun getArrangementCount(joltageList: List<Int>): Long {
    val sortedList = buildJoltageList(joltageList)
    return getJoltageArrangementCount(sortedList)
}

private fun getJoltageArrangementCount(joltageList: List<Int>): Long {
    var arrangementCount: Long = 1;

    // Find gap of 3
    for(j in 0 until joltageList.size - 1){
        val currentJoltage = joltageList[j]
        val nextJoltage = joltageList[j + 1]
        if(nextJoltage - currentJoltage == 3){
            arrangementCount *= when(j){
                2 -> 2
                3 -> 4
                4 -> 7
                else -> 1
            }
            val sublist = joltageList.subList(j+1,joltageList.size)
            println(sublist)
            arrangementCount *= getJoltageArrangementCount(sublist)
            break
        }
    }
    return arrangementCount
}

private fun buildArrangementList(joltageList: List<Int>): List<List<Int>>{
    val currentJoltage = joltageList[0]

    val indexOneGreater = joltageList.indexOf(currentJoltage + 1)
    val indexTwoGreater = joltageList.indexOf(currentJoltage + 2)
    val indexThreeGreater = joltageList.indexOf(currentJoltage + 3)

    var oneArrangementList: List<List<Int>> = listOf()
    if (indexOneGreater != -1){
        val oneSublist = joltageList.subList(indexOneGreater,joltageList.size)
        oneArrangementList = buildArrangementList(oneSublist)
    }

    var twoArrangementList: List<List<Int>> = listOf()
    if (indexTwoGreater != -1){
        val twoSublist = joltageList.subList(indexTwoGreater,joltageList.size)
        twoArrangementList = buildArrangementList(twoSublist)
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
    oneArrangementList = oneArrangementList.filter { it.contains(joltageList.last()) }

    twoArrangementList = twoArrangementList.map {
        arrangementList ->
        val mutArrangementList = arrangementList.toMutableList()
        mutArrangementList.add(0,currentJoltage)
        mutArrangementList
    }
    if (twoArrangementList.isEmpty()){
        twoArrangementList = listOf(listOf(currentJoltage))
    }
    twoArrangementList = twoArrangementList.filter { it.contains(joltageList.last()) }

    threeArrangementList = threeArrangementList.map {
        arrangementList ->
        val mutArrangementList = arrangementList.toMutableList()
        mutArrangementList.add(0,currentJoltage)
        mutArrangementList
    }
    if (threeArrangementList.isEmpty()){
        threeArrangementList = listOf(listOf(currentJoltage))
    }
    threeArrangementList = threeArrangementList.filter { it.contains(joltageList.last()) }

    val combinedList = oneArrangementList + twoArrangementList + threeArrangementList
    return combinedList.distinct()
}

private fun buildJoltageList(joltageList: List<Int>): List<Int>{
    val sortedList = joltageList.sorted().toMutableList()
    sortedList.add(0,0)
    sortedList.add(sortedList.last() + 3)
    return sortedList
}