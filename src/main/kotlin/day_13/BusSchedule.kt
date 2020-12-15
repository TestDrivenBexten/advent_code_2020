package day_13

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

    var leastCommonMultiple = 1L
    var targetTimestamp = 0L
    for(j in busOffsetList.indices){
        val currentBusOffset = busOffsetList[j]
        val (busId, timeOffset) = currentBusOffset
        while((targetTimestamp + timeOffset.toLong() ) % busId != 0L){
            targetTimestamp += leastCommonMultiple
        }

        val busSubList = busOffsetList.subList(0,j + 1)
        leastCommonMultiple = findLeastCommonMultiple(busSubList)
    }
    return targetTimestamp
}

private fun findLeastCommonMultiple(busOffsetList: List<BusOffset>): Long {
    return busOffsetList.fold(1) { lcm, bus -> lcm * bus.busId }
}
