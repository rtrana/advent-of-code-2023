import kotlin.io.path.Path
import kotlin.io.path.readLines


class AOCDay07 {

    var handScoreMappings: MutableMap<CamelHand, Long> = mutableMapOf()

    fun parseInput(input: List<String>) {
        var handScoreMap: MutableMap<CamelHand, Long> = mutableMapOf()
        for (line in input) {
            val parts = line.split(" ")
            val hand = CamelHand(parts[0])
            handScoreMap[hand] = parts[1].toLong()
        }

        this.handScoreMappings = handScoreMap
    }

    fun calculateTotalWinnings(): Long {
        var sortedHands = this.handScoreMappings.keys.toList().sorted()
        var total: Long = 0
        for (i in sortedHands.indices) {
            total += this.handScoreMappings[sortedHands[i]]!! * (i + 1)
        }
        return total
    }

}

fun main() {
    val input = Path("src/main/resources/inputDay07.txt").readLines()
    val construct = AOCDay07()
    construct.parseInput(input)
    println(construct.calculateTotalWinnings())
}