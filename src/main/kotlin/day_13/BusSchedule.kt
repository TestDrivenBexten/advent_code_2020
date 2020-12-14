package day_13

import java.sql.Timestamp

data class Bus(val busId: Int, val timestamp: Int)

fun findNextBus(currentTimestamp: Int, busIdList: List<Int>): Bus {
    var currentTime = currentTimestamp
    val sortedIdList = busIdList.sortedDescending()

    while(currentTime < 10000000){
        sortedIdList.map { busId ->
            if(currentTime % busId == 0){
                return Bus(busId, currentTime)
            }
        }
        currentTime += 1
    }
    return Bus(0,0)
}

fun parseBusList(busString: String): List<Int> {
    val busIdStringList = busString.split(",")
    val filteredIdList = busIdStringList.filter { it != "x" }
    return filteredIdList.map { it.toInt() }.sorted()
}

data class BusOffset(val busId: Int, val timeOffset: Int)

fun findSynchronizedTimestamp(busString: String): Long {
    val busIdStringList = busString.split(",")
    val busOffsetList = mutableListOf<BusOffset>()
    for(j in busIdStringList.indices){
        val busIdString = busIdStringList[j]
        if(busIdString != "x"){
            val busId = busIdString.toInt()
            busOffsetList.add(BusOffset(busId, j))
        }
    }

    busOffsetList.sortByDescending { it.busId }
    val (greatestId, timeOffset) = busOffsetList[0]

    println(busOffsetList)
    var targetTimestamp = 0L
    while(targetTimestamp < 1076000L){
        val isValidTimestamp = checkOffsetList(targetTimestamp, busOffsetList)
        if(isValidTimestamp){
            return targetTimestamp
        }
        targetTimestamp += 1
    }
    return 0
}

private fun checkOffsetList(targetTimestamp: Long,
                            busOffsetList: List<BusOffset>): Boolean{
    return busOffsetList.all { busOffset ->
        val (busId, timeOffset) = busOffset
        (targetTimestamp + timeOffset.toLong() ) % busId == 0L
    }
}