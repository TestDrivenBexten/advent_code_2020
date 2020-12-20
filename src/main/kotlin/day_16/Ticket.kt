package day_16

data class Ticket(val fieldValueList: List<Int>)
data class TicketConfig(val yourTicket: Ticket, val nearbyTicketList: List<Ticket>)

private val fieldRuleRegex = "(.+):\\s(\\d+)-(\\d+)\\sor\\s(\\d+)-(\\d+)"

fun getTicketErrorRate(ticketConfig: TicketConfig): Int {
    return 0
}

fun loadTicketConfig(rawInput: List<String>): TicketConfig{


    val yourTicket = readYourTicket(rawInput)
    val nearbyTicketList = readNearbyTicketList(rawInput)
    return TicketConfig(yourTicket, nearbyTicketList)
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

private fun readTicketRules(){

}

private fun readTicket(ticketInput: String): Ticket{
    val fieldValueList = ticketInput.split(",").map { Integer.parseInt(it) }
    return Ticket(fieldValueList)
}
