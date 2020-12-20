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
    return Ticket(listOf(1))
}

private fun readNearbyTicketList(rawInput: List<String>): List<Ticket> {
    return listOf(Ticket(listOf(3)))
}

private fun readTicketRules(){

}

private fun readTicket(){

}
