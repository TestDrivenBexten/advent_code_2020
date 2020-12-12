package day_11

enum class Seat {
    FLOOR, EMPTY, OCCUPIED
}

fun loadSeatingFromString(seatingList: List<String>): List<List<Seat>> {
    val seatingLayout = mutableListOf<List<Seat>>()
    seatingList.map { seatingString ->
        val seatingRow = seatingString.map { seat ->
            when(seat){
                'L' -> Seat.EMPTY
                else -> Seat.FLOOR
            }
        }
        seatingLayout.add(seatingRow)
    }
    return seatingLayout
}