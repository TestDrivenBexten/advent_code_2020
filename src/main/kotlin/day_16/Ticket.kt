package day_16

data class Ticket(val fieldValueList: List<Int>)
data class TicketConfig(val yourTicket: Ticket, val nearbyTicketList: List<Ticket>)

fun getTicketErrorRate(ticketConfig: TicketConfig): Int {
    return 0
}

fun loadTicketConfig(rawInput: List<String>): TicketConfig{


    val yourTicket = Ticket(listOf(1))
    val nearbyTicketList = listOf(Ticket(listOf(3)))
    return TicketConfig(yourTicket, nearbyTicketList)
}

private fun readTicketRules(){

}

private fun readTicket(){

}
