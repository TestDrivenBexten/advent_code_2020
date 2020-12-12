package day_11

enum class Seat {
    FLOOR, EMPTY, OCCUPIED
}

class SeatingLayout private constructor(seatList: List<List<Seat>>){
    private var seatingLayoutList = seatList
    companion object Factory {
        fun loadSeatingFromString(seatingList: List<String>): SeatingLayout {
            val seatingLayout = mutableListOf<List<Seat>>()
            seatingList.map { seatingString ->
                val seatingRow = seatingString.map { seat ->
                    when(seat){
                        'L' -> Seat.EMPTY
                        '#' -> Seat.OCCUPIED
                        else -> Seat.FLOOR
                    }
                }
                seatingLayout.add(seatingRow)
            }
            return SeatingLayout(seatingLayout)
        }
    }

    fun advanceRound(){
        val rowCount = seatingLayoutList.size
        val colCount = seatingLayoutList[0].size

        val nextSeatingLayout = mutableListOf<List<Seat>>()
        for(row in 0 until rowCount){
            val seatingRow = mutableListOf<Seat>()
            for(col in 0 until colCount){
                val currentSeat = seatingLayoutList[row][col]
                if(currentSeat == Seat.EMPTY){
                    seatingRow.add(Seat.OCCUPIED)
                }else{
                    seatingRow.add(currentSeat)
                }
            }
            nextSeatingLayout.add(seatingRow)
        }
        seatingLayoutList = nextSeatingLayout
    }

    fun displayLayout(){
        seatingLayoutList.map {
            seatingRow -> println(seatingRow)
        }
    }

    fun hasSameLayout(layout: SeatingLayout): Boolean {
        return this.seatingLayoutList == layout.seatingLayoutList
    }

    fun getSeatCount(seatEnum: Seat): Int{
        return this.seatingLayoutList.fold(0) { sum, seatingRow ->
            sum + seatingRow.fold(0) {seatCount, seat ->
                if (seat == seatEnum){
                    seatCount + 1
                } else {
                    seatCount
                }
            }
        }
    }
}
