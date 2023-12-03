import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay02 {

    fun initialFileParsing(lines: List<String>):  MutableList<String>{
        val games: MutableList<String> = mutableListOf()
        for (line in lines) {
            val gameSubsets = line.split(": ")[1].split("; ").joinToString(", ")
            games.add(gameSubsets)
        }
        return games
    }

    fun getMaxColorsPerGame(games: List<String>): MutableMap<Int, MutableMap<String, Int>> {
        var colorMap: MutableMap<String, Int> = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        var gameMap: MutableMap<Int, MutableMap<String, Int>> = mutableMapOf()
        for (index in games.indices) {
            var subset = games[index].split(", ")
            for (balls in subset) {
                var parts = balls.split(" ")
                if (colorMap[parts[1]]!! < parts[0].toInt())
                    colorMap[parts[1]] = parts[0].toInt()
            }
            gameMap[index + 1] = colorMap
            colorMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        }
        return gameMap
    }

    fun findPossibleGames(games: List<String>, red: Int, green: Int, blue: Int): Int {
        val gameMap = getMaxColorsPerGame(games)
        var sum = 0
        for (key in gameMap.keys) {
            if (red >= gameMap[key]!!["red"]!! && green >= gameMap[key]!!["green"]!!
                && blue >= gameMap[key]!!["blue"]!!)
                sum += key
        }
        return sum
    }

    fun getPower(games: List<String>): BigInteger {
        val gameMap = getMaxColorsPerGame(games)
        var gameProduct = BigInteger.ONE
        var powerSum = BigInteger.ZERO
        for (key in gameMap.keys) {
            for (color in gameMap[key]!!.keys)
                gameProduct = gameProduct.multiply(BigInteger(gameMap[key]!![color].toString()))

            powerSum = powerSum.add(gameProduct)
            gameProduct = BigInteger.ONE
        }
        return powerSum
    }
}

fun main() {
    val input = Path("src/main/resources/inputDay02.txt").readLines()
    val games = AOCDay02().initialFileParsing(input)
    println(AOCDay02().findPossibleGames(games, 12, 13, 14))
    println(AOCDay02().getPower(input))
}
