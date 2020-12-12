package day_11

import java.lang.Integer.max
import java.lang.Integer.min

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

    // Will run until seats stop changing
    fun autoAdvance(){
        val maxRun = 100; // Avoid infinite loop
        var currentSeatingLayout = listOf<List<Seat>>()

        var currentRun = 0
        while(currentRun < maxRun && this.seatingLayoutList != currentSeatingLayout){
            currentSeatingLayout = this.seatingLayoutList
            advanceRound()
            currentRun++
            println(currentRun)
        }
    }

    fun advanceRound(){
        val rowCount = seatingLayoutList.size
        val colCount = seatingLayoutList[0].size

        val nextSeatingLayout = mutableListOf<List<Seat>>()
        for(row in 0 until rowCount){
            val seatingRow = mutableListOf<Seat>()
            for(col in 0 until colCount) {
                val currentSeat = seatingLayoutList[row][col]

                if (currentSeat == Seat.EMPTY) {
                    val occupiedSeatCount = countAdjacentOccupiedSeats(row, col)
                    if (occupiedSeatCount == 0) {
                        seatingRow.add(Seat.OCCUPIED)
                    } else {
                        seatingRow.add(Seat.EMPTY)
                    }
                } else if (currentSeat == Seat.OCCUPIED) {
                    val occupiedSeatCount = countAdjacentOccupiedSeats(row, col)
                    if(occupiedSeatCount >= 4){
                        seatingRow.add(Seat.EMPTY)
                    } else {
                        seatingRow.add(Seat.OCCUPIED)
                    }
                } else {
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

    private fun countAdjacentOccupiedSeats(row: Int, col: Int): Int{
        val rowCount = seatingLayoutList.size
        val colCount = seatingLayoutList[0].size

        var occupiedCount = 0

        // Column Limits
        val previousCol = max(0, col - 1)
        val nextCol = min(colCount - 1, col + 1)

        // Count previous row
        if(row > 0){
            val previousRow = row - 1
            for(j in previousCol..nextCol){
                val currentSeat = seatingLayoutList[previousRow][j]
                if(currentSeat == Seat.OCCUPIED){
                    occupiedCount++
                }
            }
        }

        // Count current row
        for(j in previousCol..nextCol){
            if(j == col){
                continue
            }
            val currentSeat = seatingLayoutList[row][j]
            if(currentSeat == Seat.OCCUPIED){
                occupiedCount++
            }
        }

        // Count next row
        if(row < rowCount - 1){
            val nextRow = row + 1
            for(j in previousCol..nextCol){
                val currentSeat = seatingLayoutList[nextRow][j]
                if(currentSeat == Seat.OCCUPIED){
                    occupiedCount++
                }
            }
        }

        return occupiedCount
    }
}
