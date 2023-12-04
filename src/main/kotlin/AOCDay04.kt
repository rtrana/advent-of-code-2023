import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay04(val input: List<String>) {

    var lottoTickets: MutableMap<Int, MutableList<MutableSet<Int>>> = getNumberSets()
    var ticketCount: Array<Int> = Array(this.lottoTickets.keys.size){1}

    private fun getNumberSets(): MutableMap<Int, MutableList<MutableSet<Int>>> {
        var lottoTickets: MutableMap<Int, MutableList<MutableSet<Int>>> = mutableMapOf()
        for (line in this.input.indices){
            var numbers = this.input[line].split(": ")[1].split("|")
            var lottoNumbers: MutableList<MutableSet<Int>> = mutableListOf()
            var numberSet: MutableSet<Int> = mutableSetOf()
            for (i in 0 .. 1) {
                var numList = numbers[i].trim().replace("  ", " ").split(" ")
                for (num in numList) {
                    numberSet.add(num.toInt())
                }
                lottoNumbers.add(numberSet)
                numberSet = mutableSetOf()
            }
            lottoTickets[line] = lottoNumbers
        }
        return lottoTickets
    }

    fun getTicketCountsForGame(ticket: Int) {
        var winners = this.lottoTickets[ticket]!![0].intersect(this.lottoTickets[ticket]!![1])
        var index = ticket + 1
        while (index <= ticket + winners.size && index < this.ticketCount.size) {
            this.ticketCount[index] += (1 * this.ticketCount[ticket])
            index++
        }
    }

    fun getTotalTickets():Int {
        for (ticket in this.lottoTickets.keys) {
            getTicketCountsForGame(ticket)
        }
        return this.ticketCount.sum()
    }

    fun getPointsForTicket(ticket: Int):Int {
        var winners = this.lottoTickets[ticket]!![0].intersect(this.lottoTickets[ticket]!![1])
        var sum = 0
        for (index in winners.indices)
            if (index == 0)
                sum += 1
            else
                sum *= 2
        return sum
    }

    fun getSumOfTicketPoints():Int {
        var sum = 0
        for (ticket in this.lottoTickets.keys)
            sum += getPointsForTicket(ticket)
        return sum
    }

}

fun main() {
    val input = Path("src/main/resources/inputDay04.txt").readLines()
    val day4 = AOCDay04(input)
    println(day4.getSumOfTicketPoints())
    println(day4.getTotalTickets())
}