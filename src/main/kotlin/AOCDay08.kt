import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay08(val input: List<String>) {

    private var networkMap: MutableMap<String, Pair<String, String>> = mutableMapOf()
    private var directions: String = ""

    init {
        parseInput()
    }

    private fun parseInput() {
        this.directions = input[0]
        for (i in 2..< input.size) {
            var splitLine = input[i].split(" = ")
            var nodes = splitLine[1].replace("[()]".toRegex(), "").split(", ")
            this.networkMap[splitLine[0]] = Pair(nodes[0], nodes[1])
        }
    }

    fun countNodeStops(start: String, endsWith: String): Int {
        var count = 0
        var currentNode = start
        while (!currentNode.endsWith(endsWith)) {
            if (this.directions[count] == 'L')
                currentNode = this.networkMap[currentNode]!!.first
            else
                currentNode = this.networkMap[currentNode]!!.second

            count++
            if (count == this.directions.length && !currentNode.endsWith(endsWith))
                this.directions += this.directions
        }
        return count
    }

    fun getStartingNodes(): List<String> {
        return this.networkMap.keys.toList().filter { it -> it.endsWith('A') }.toList()
    }

    fun getCountToAllEndingNodes(): Long {
        var cycles: MutableList<Int> = mutableListOf()
        var nodes = getStartingNodes()
        for (node in nodes) {
            var cycle = countNodeStops(node, "Z")
            cycles.add(cycle)
        }

        var lcm: Long = cycles[0].toLong()
        for (cycle in cycles) {
            lcm = lcm(cycle.toLong(), lcm)
        }
        return lcm
    }

    fun lcm(first: Long, second: Long): Long {
        var remainder: Long
        var gcd = first
        var hold = second
        val zero: Long = 0
        while (hold != zero) {
            remainder = gcd % hold
            gcd = hold
            hold = remainder
        }
        return (first / gcd * second)
    }
}

fun main() {
    val input = Path("src/main/resources/inputDay08-2.txt").readLines()
    val construct = AOCDay08(input)
    println(construct.countNodeStops("AAA", "ZZZ"))
    println(construct.getCountToAllEndingNodes())
}