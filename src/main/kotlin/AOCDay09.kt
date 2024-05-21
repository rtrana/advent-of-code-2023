import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

class AOCDay09 {

    fun getExtrapolatedValue(nums: List<Long>): Long {
        var endsOfLists: MutableList<Long> = mutableListOf()
        var currentNumList: MutableList<Long> = nums.toMutableList()
        var count = Int.MAX_VALUE
        while (count != 0) {
            var tempList: MutableList<Long> = mutableListOf()
            endsOfLists.add(currentNumList[currentNumList.lastIndex])
            for (i in 0..< currentNumList.size - 1) {
                tempList.add(currentNumList[i + 1] - currentNumList[i])
            }
            val zero: Long = 0
            count = tempList.count { it -> it != zero }
            if (count != 0)
                currentNumList = tempList
        }

        return endsOfLists.sum()
    }

    fun addExtrapolatedValues(report: List<List<Long>>): Long {
        var total: Long = 0
        for (sequence in report) {
            total += getExtrapolatedValue(sequence)
        }
        return total
    }
}

fun parseInput(input: List<String>): List<List<Long>> {
    var report: MutableList<MutableList<Long>> = mutableListOf()
    for (line in input) {
        report.add(line.split(" ").map { it.toLong() }.toMutableList())
    }
    return report
}

fun main() {
    val input = Path("src/main/resources/inputDay09-2.txt").readLines()
    val report = parseInput(input)
    println(AOCDay09().addExtrapolatedValues(report))
}