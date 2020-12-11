package day_10

data class JoltageDifference(val oneJoltCount: Int, val threeJoltCount: Int)

fun getJoltageDifference(joltageList: List<Int>): JoltageDifference{
    val sortedList = joltageList.sorted().toMutableList()
    sortedList.add(0,0)
    sortedList.add(joltageList.last() + 3)

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