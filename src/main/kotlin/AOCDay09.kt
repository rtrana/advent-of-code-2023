import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay09 {

    fun getExtrapolatedValues(nums: List<Long>): List<Long> {
        var endsOfLists: MutableList<Long> = mutableListOf()
        var frontsOfLists: MutableList<Long> = mutableListOf()
        var currentNumList: MutableList<Long> = nums.toMutableList()
        var count = Int.MAX_VALUE
        while (count != 0) {
            var tempList: MutableList<Long> = mutableListOf()
            frontsOfLists.add(currentNumList[0])
            endsOfLists.add(currentNumList[currentNumList.lastIndex])
            for (i in 0..< currentNumList.size - 1) {
                tempList.add(currentNumList[i + 1] - currentNumList[i])
            }
            val zero: Long = 0
            count = tempList.count { it -> it != zero }
            if (count != 0)
                currentNumList = tempList
        }

        var start: Long = 0
        var reversedFront = frontsOfLists.reversed()
        for (i in reversedFront.indices) {
            start = reversedFront[i] - start
        }
        return listOf(endsOfLists.sum(), start )
    }

    fun calculateExtrapolatedValues(report: List<List<Long>>): List<Long> {
        var total: Long = 0
        var totalFront: Long = 0
        for (sequence in report) {
            var extrapolations = getExtrapolatedValues(sequence)
            total += extrapolations[0]
            totalFront += extrapolations[1]
        }
        return listOf(total, totalFront)
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
    println(AOCDay09().calculateExtrapolatedValues(report))
}