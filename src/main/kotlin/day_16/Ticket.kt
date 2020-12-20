package day_16

import java.util.regex.Pattern

data class Ticket(val fieldValueList: List<Int>)
data class FieldRange (val rangeMin: Int, val rangeMax: Int)
data class TicketRule(val fieldName: String,
                      val lowerRange: FieldRange,
                      val upperRange: FieldRange)
data class TicketConfig(val ticketRuleList: List<TicketRule>,
                        val yourTicket: Ticket,
                        val nearbyTicketList: List<Ticket>)

private const val fieldRuleRegex = "(.+):\\s(\\d+)-(\\d+)\\sor\\s(\\d+)-(\\d+)"

fun getTicketErrorRate(ticketConfig: TicketConfig): Int {
    return 0
}

fun loadTicketConfig(rawInput: List<String>): TicketConfig{
    val ticketRuleList = readTicketRuleList(rawInput)
    val yourTicket = readYourTicket(rawInput)
    val nearbyTicketList = readNearbyTicketList(rawInput)
    return TicketConfig(ticketRuleList, yourTicket, nearbyTicketList)
}

fun combineTicketRanges(rangeList: List<FieldRange>): List<FieldRange>{
    val newRangeList = mutableListOf<FieldRange>()

    val rangeHead = rangeList.first()
    val rangeTail = rangeList.drop(1)

    return listOf()
}

private fun hasRangeOverlap(firstRange: FieldRange, secondRange: FieldRange): Boolean {
    val (firstMin, firstMax) = firstRange
    val (secondMin, secondMax) = secondRange

    val firstMinOverlap = firstMin > secondMin && firstMin < secondMax
    val firstMaxOverlap = firstMax > secondMin && firstMax < secondMax
    return firstMinOverlap || firstMaxOverlap
}

private fun readYourTicket(rawInput: List<String>): Ticket {
    val ticketIndex = rawInput.indexOf("your ticket:") + 1
    val ticketInput = rawInput[ticketIndex]
    return readTicket(ticketInput)
}

private fun readNearbyTicketList(rawInput: List<String>): List<Ticket> {
    val ticketListIndex = rawInput.indexOf("nearby tickets:") + 1
    val ticketInputList = rawInput.subList(ticketListIndex,rawInput.size)
    return ticketInputList.map { readTicket(it) }
}

private fun readTicketRuleList(rawInput: List<String>): List<TicketRule>{
    val ticketRuleList = mutableListOf<TicketRule>()

    val fieldRulePattern = Pattern.compile(fieldRuleRegex)
    for(inputRow in rawInput){
        val fieldRuleMatcher = fieldRulePattern.matcher(inputRow)
        if(fieldRuleMatcher.matches()){
            val fieldName = fieldRuleMatcher.group(1)

            val minRangeStart = Integer.parseInt(fieldRuleMatcher.group(2))
            val minRangeEnd = Integer.parseInt(fieldRuleMatcher.group(3))
            val minRange = FieldRange(minRangeStart, minRangeEnd)

            val maxRangeStart = Integer.parseInt(fieldRuleMatcher.group(4))
            val maxRangeEnd = Integer.parseInt(fieldRuleMatcher.group(5))
            val maxRange = FieldRange(maxRangeStart, maxRangeEnd)

            val ticketRule = TicketRule(fieldName, minRange, maxRange)
            ticketRuleList.add(ticketRule)
        }
    }
    return ticketRuleList
}

private fun readTicket(ticketInput: String): Ticket{
    val fieldValueList = ticketInput.split(",").map { Integer.parseInt(it) }
    return Ticket(fieldValueList)
}
