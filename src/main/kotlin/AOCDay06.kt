import kotlin.io.path.Path
import kotlin.io.path.readLines


class AOCDay06() {

    var times: MutableList<Long> = mutableListOf()
    var records: MutableList<Long> = mutableListOf()

    fun parseInput(input: List<String>, problem: Int) {
        var intLine = input[0].replace(".*: +".toRegex(), "")
        if (problem == 1)
            this.times = intLine.split(" +".toRegex()).map { it -> it.toLong() }.toMutableList()
        else
            this.times.add(intLine.replace(" +".toRegex(), "").toLong())

        intLine = input[1].replace(".*: +".toRegex(), "")
        if (problem == 1)
            this.records = intLine.split(" +".toRegex()).map { it -> it.toLong() }.toMutableList()
        else
            this.records.add(intLine.replace(" +".toRegex(), "").toLong())
    }

    fun multiplyWaysForRaces(): Long {
        var total: Long = 1
        for (i in 0..< this.times.size) {
            var boundary = findMinimumBoundary(this.times[i], this.records[i])
            var ways = calculateTotalNumberOfWays(this.times[i], boundary)
            total *= ways
        }
        return total
    }

    fun calculateTotalNumberOfWays(time: Long, boundary: Long): Long {
        var ways: Long
        var notFound: Long = -1
        if (boundary == notFound)
            ways = 0
        else if (time.mod(2) == 0)
            ways = (time.floorDiv(2) - boundary) * 2 + 1
        else
            ways = ((time.floorDiv(2) - boundary) + 1) * 2
        return ways
    }

    fun findMinimumBoundary(time: Long, record: Long): Long {
        var left: Long = 0
        var right = time.floorDiv(2)
        while (left != right) {
            var mid = (right + left).floorDiv(2)
            if (mid == left)
                right = mid
            else if (mid == right)
                left = mid
            else if (mid * (time - mid) > record)
                right = mid
            else
                left = mid
        }
        var minBoundary: Long
        if (left * (time - left) <= record)
            minBoundary = left + 1
        else
            minBoundary = left
        if (minBoundary == time.floorDiv(2) && minBoundary * (time - minBoundary) <= record)
            minBoundary = -1
        return minBoundary
    }

}

fun main() {
    val input = Path("src/main/resources/inputDay06.txt").readLines()
    var construct = AOCDay06()
    construct.parseInput(input, 2)
    println(construct.multiplyWaysForRaces())
}