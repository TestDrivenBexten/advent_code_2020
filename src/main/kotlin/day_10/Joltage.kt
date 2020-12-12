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
            arrangementCount *= getJoltageArrangementCount(sublist)
            break
        }
    }
    return arrangementCount
}

private fun buildJoltageList(joltageList: List<Int>): List<Int>{
    val sortedList = joltageList.sorted().toMutableList()
    sortedList.add(0,0)
    sortedList.add(sortedList.last() + 3)
    return sortedList
}