import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay03(input: List<String>) {

    var schematic: Array<CharArray> = convertInputTo2DArray(input)
    var coordinateMap = Array(this.schematic.size){Array(this.schematic[0].size){ mutableListOf(-1, -1) }}
    var positionNumberMap: Map<List<Int>, MutableList<Int>> = mutableMapOf()

    fun convertInputTo2DArray(input: List<String>): Array<CharArray> {
        this.schematic = Array(input.size){ CharArray(input[0].length) }
        for (index in input.indices) {
            this.schematic[index] = input[index].toCharArray()
        }
        return schematic
    }

    fun mapSymbolCoordinates() {
        var rowEnd = this.schematic.size
        var colEnd = this.schematic[0].size
        for (i in 0..< rowEnd)
            for (j in 0..< colEnd - 1) {
                if (this.schematic[i][j].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i][j + 1] = mutableListOf(i, j)
                if (this.schematic[i][j + 1].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i][j] = mutableListOf(i, j + 1)
            }

        for (i in 0..< rowEnd - 1)
            for (j in 0..< colEnd - 1) {
                if (this.schematic[i][j].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i + 1][j + 1] = mutableListOf(i, j)
                if (this.schematic[i + 1][j + 1].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i][j] = mutableListOf(i + 1, j + 1)
            }

        for (i in 1..< rowEnd)
            for (j in 0..< colEnd - 1) {
                if (this.schematic[i][j].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i - 1][j + 1] = mutableListOf(i, j)
                if (this.schematic[i - 1][j + 1].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i][j] = mutableListOf(i - 1, j + 1)
            }

        for (i in 1..< rowEnd)
            for (j in 0..< colEnd) {
                if (this.schematic[i][j].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i - 1][j] = mutableListOf(i, j)
                if (this.schematic[i-1][j].toString().contains("[^0-9.]".toRegex()))
                    this.coordinateMap[i][j] = mutableListOf(i-1, j)
            }
    }

    fun findPartNumberSum():Int {
        for (row in this.schematic.indices)
            findNumbersForSymbols(row)

        var sum = 0
        for (lst in this.positionNumberMap.values) {
            for (value in lst) {
                sum += value
            }
        }
        return sum
    }

    fun findNumbersForSymbols(row: Int) {
        var isPartNumber = false
        var coordinate = listOf(-1, -1)
        var number = ""
        for (col in this.schematic[row].indices) {
            val ch = this.schematic[row][col]
            if (ch.isDigit()) {
                number += ch.toString()
                if (!isPartNumber) {
                    isPartNumber = this.coordinateMap[row][col] != mutableListOf(-1, -1)
                    if (isPartNumber)
                        coordinate = this.coordinateMap[row][col]
                }
                if (col == this.schematic[row].size - 1 && isPartNumber)
                    addToPositionMap(coordinate, number)
            } else if (number != "" && isPartNumber) {
                addToPositionMap(coordinate, number)
                number = ""
                isPartNumber = false
                coordinate = listOf(-1, -1)
            } else {
                number = ""
            }
        }
    }

    private fun addToPositionMap(coordinate: List<Int>, number: String) {
        if (this.positionNumberMap[coordinate] == null) {
            this.positionNumberMap = this.positionNumberMap.plus(coordinate to mutableListOf(number.toInt()))
        } else
            this.positionNumberMap[coordinate]!!.add(number.toInt())
    }

    fun calculateGearProduct(): BigInteger {
        var sum = BigInteger.ZERO
        for (key in this.positionNumberMap.keys) {
            if (this.schematic[key[0]][key[1]] == '*' && this.positionNumberMap[key]!!.size == 2) {
                var product = BigInteger.ONE
                for (value in this.positionNumberMap[key]!!) {
                    product = product.multiply(BigInteger(value.toString()))
                }
                sum = sum.add(product)
            }
        }
        return sum
    }


}

fun main() {
    val input = Path("src/main/resources/inputDay03.txt").readLines()
    val day3 = AOCDay03(input)
    day3.mapSymbolCoordinates()
    println(day3.findPartNumberSum())
    println(day3.calculateGearProduct())
}

fun print2DArray(array: Array<Array<MutableList<Int>>>) {
    for (row in array) {
        for (col in row)
            print(col.toString() + " ")
        println()
    }
}
