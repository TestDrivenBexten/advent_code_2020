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

fun findSynchronizedTimestamp(busString: String): Long {
    return 0
}