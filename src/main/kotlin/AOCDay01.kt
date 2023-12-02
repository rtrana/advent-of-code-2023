import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/main/resources/inputDay01.txt").readLines()
    println(part1(input))
    println(part2(input))
}

fun part1(lines: List<String>): Int {
    return lines.map { it: String ->
        it.replace("[^0-9]".toRegex(), "")
    }
        .map { it: String -> it.first().digitToInt() * 10 + it.last().digitToInt() }
        .sum()
}

fun part2(input: List<String>): Int {
    val lines = input.toMutableList()
    val wordNumberMap = mapOf(
        "one" to "1", "two" to "2", "three" to "3", "four" to "4",
        "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )

    fun setFirstOrLast(valueFound: Boolean, digit: String, digits: Array<String>) {
        if (valueFound && digits[0] == "") {
            digits[0] = digit
            digits[1] = digit
        } else if (valueFound)
            digits[1] = digit
    }

    fun findWords(lines: MutableList<String>, index: Int, chIndex: Int, digits: Array<String>) {
        // Runtime O(1) because there are always 9 keys and the maximum key size is 5 so it will
        // run a maximum constant time of 45 for any iteration
        for (key in wordNumberMap.keys) {
            var wordNumberFound = lines[index].substring(chIndex).startsWith(key)
            var digit = wordNumberMap[key].toString()
            setFirstOrLast(wordNumberFound, digit, digits)
        }
    }

    fun findFirstAndLast(lines: MutableList<String>, index: Int, digits: Array<String>) {
        // Runtime is O(m) where m is the number of characters in the given string
        for (chIndex in lines[index].indices) {
            var ch = lines[index][chIndex].toString()
            val isDigit = lines[index][chIndex].isDigit()
            setFirstOrLast(isDigit, ch, digits)
            findWords(lines, index, chIndex, digits)
        }
    }

    // Total runtime is O(n*m) where n is the number of strings in the list
    // and m is the length of each string
    var sum = 0
    for (index in lines.indices) {
        var digits = Array<String>(2) {""}
        findFirstAndLast(lines, index, digits)
        sum += (digits[0] + digits[1]).toInt()
    }
    return sum
}
