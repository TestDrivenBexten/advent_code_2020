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
    val fieldRangeList = ticketConfig.ticketRuleList.flatMap { listOf(it.lowerRange,it.upperRange) }
    val nearbyFieldList = ticketConfig.nearbyTicketList.flatMap {  it.fieldValueList }

    return nearbyFieldList.fold(0) { sum, field ->
        if(isFieldWithinRange(field,fieldRangeList)){
            sum
        } else {
            sum + field
        }
    }
}

fun getDepartureProduct(ticketConfig: TicketConfig): Int {
    return -1
}

fun getTicketFieldOrder(ticketConfig: TicketConfig): Map<String, Int> {
    val fieldOrderMap = mutableMapOf<String,Int>()

    val validTicketList = ticketConfig.nearbyTicketList
            .filter { isTicketValid(it,ticketConfig.ticketRuleList) }

    val ticketColumnList = mutableMapOf<Int,List<Int>>()
    val fieldCount = ticketConfig.ticketRuleList.size
    for(index in 0 until fieldCount){
        ticketColumnList[index] = validTicketList.map { it.fieldValueList[index] }
    }

    val possibleFieldOrderMap = mutableMapOf<Int,MutableList<TicketRule>>()
    for(index in 0 until fieldCount){
        val ticketColumn = ticketColumnList[index]
        val ruleList = ticketConfig.ticketRuleList
        val possibleFieldList = ruleList.filter { rule ->
            ticketColumn!!.all { isFieldWithinRange(it, listOf(rule.lowerRange,rule.upperRange)) }
        }
        possibleFieldOrderMap.put(index + 1,possibleFieldList.toMutableList())
    }

    while(possibleFieldOrderMap.isNotEmpty()){
        val soleFieldList = possibleFieldOrderMap.filter { it.value.size == 1 }
        soleFieldList.map { fieldOrderMap.put(it.value.first().fieldName,it.key) }
        soleFieldList.map { possibleFieldOrderMap.remove(it.key) }

        possibleFieldOrderMap.map { possibleFieldOrder ->
            val possibleColumnList = possibleFieldOrder.value.map { it }
            val remainingColumnList = possibleColumnList.filterNot { fieldOrderMap.containsKey(it.fieldName) }
            possibleFieldOrderMap.put(possibleFieldOrder.key,remainingColumnList.toMutableList())
        }
    }

    return fieldOrderMap
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

private fun isTicketValid(ticket: Ticket, ticketRuleList: List<TicketRule>): Boolean {
    val fieldRangeList = ticketRuleList.flatMap { listOf(it.lowerRange,it.upperRange) }
    return ticket.fieldValueList.all { isFieldWithinRange(it, fieldRangeList) }
}

private fun isFieldWithinRange(fieldValue: Int, fieldList: List<FieldRange>): Boolean{
    return fieldList.any { it.rangeMin <= fieldValue && it.rangeMax >= fieldValue}
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
