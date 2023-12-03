import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay03(input: List<String>) {

    var schematic: Array<CharArray> = convertInputTo2DArray(input)
    var gears: Array<CharArray> = Array(input.size){ CharArray(input[0].length) }
    var gearNumbers: Array<IntArray> = Array(input.size){ IntArray(input[0].length) }

    fun convertInputTo2DArray(input: List<String>): Array<CharArray> {
        this.schematic = Array(input.size){ CharArray(input[0].length) }
        for (index in input.indices) {
            this.schematic[index] = input[index].toCharArray()
        }
        return schematic
    }

    fun checkForSymbols(row: Int, col: Int, regex: Regex, gearCount: Boolean, array: Array<CharArray>): Boolean {
        var neighbors = ""

        fun corners() {
            if (row == 0 && col == 0)
                neighbors = array[row+1][col].toString() + array[row][col+1].toString() +
                        array[row+1][col+1].toString()
            else if (row == 0 && col == array[0].size - 1)
                neighbors = array[row][col-1].toString() + array[row+1][col].toString() +
                        array[row+1][col-1].toString()
            else if (row == array.size - 1 && col == 0)
                neighbors = array[row-1][col].toString() + array[row-1][col+1].toString() +
                        array[row][col+1].toString()
            else if (row == array.size - 1 && col == array[0].size - 1)
                neighbors = array[row-1][col].toString() + array[row-1][col-1].toString() +
                        array[row][col-1].toString()
        }

        fun firstOrLastRow() {
            if (row == 0 && neighbors == "")
                neighbors = array[row+1][col].toString() + array[row][col+1].toString() +
                        array[row+1][col+1].toString() + array[row+1][col-1].toString() +
                        array[row][col-1].toString()
            else if (row == array.size - 1 && (col != 0 && col != array[0].size - 1))
                neighbors = array[row-1][col].toString() + array[row][col-1].toString() +
                        array[row][col+1].toString() + array[row-1][col+1].toString() +
                        array[row-1][col-1].toString()
        }

        fun firstOrLastColumn() {
            if (col == 0 && neighbors == "")
                neighbors = array[row-1][col].toString() + array[row+1][col].toString() +
                        array[row][col+1].toString() + array[row-1][col+1].toString() +
                        array[row+1][col+1].toString()
            else if (col == array[0].size - 1 && (row != 0 && row != array.size - 1))
                neighbors = array[row-1][col].toString() + array[row+1][col].toString() +
                        array[row][col-1].toString() + array[row-1][col-1].toString() +
                        array[row+1][col-1].toString()
        }

        corners()
        firstOrLastRow()
        firstOrLastColumn()

        if (neighbors == "")
            neighbors = array[row-1][col].toString() + array[row+1][col].toString() +
                array[row][col-1].toString() + array[row][col+1].toString() +
                array[row-1][col+1].toString() + array[row-1][col-1].toString() +
                array[row+1][col+1].toString() + array[row+1][col-1].toString()

        if (gearCount) {
            return neighbors.replace("[^1]".toRegex(), "").length == 2
        } else
            return neighbors.contains(regex)
    }

    fun multiplyGears(row: Int, col: Int): BigInteger {
        var product = BigInteger.ONE
        var numbers: List<Int> = mutableListOf()

        fun corners() {
            if (row == 0 && col == 0)
                numbers = mutableListOf(this.gearNumbers[row+1][col], this.gearNumbers[row][col+1], 
                        this.gearNumbers[row+1][col+1])
            else if (row == 0 && col == this.gearNumbers[0].size - 1)
                numbers = mutableListOf(this.gearNumbers[row][col-1], this.gearNumbers[row+1][col],
                        this.gearNumbers[row+1][col-1])
            else if (row == this.gearNumbers.size - 1 && col == 0)
                numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row-1][col+1], 
                        this.gearNumbers[row][col+1])
            else if (row == this.gearNumbers.size - 1 && col == this.gearNumbers[0].size - 1)
                numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row-1][col-1],
                        this.gearNumbers[row][col-1])
        }

        fun firstOrLastRow() {
            if (row == 0 && numbers.isEmpty())
                numbers = mutableListOf(this.gearNumbers[row+1][col], this.gearNumbers[row][col+1],
                        this.gearNumbers[row+1][col+1], this.gearNumbers[row+1][col-1],
                        this.gearNumbers[row][col-1])
            else if (row == this.gearNumbers.size - 1 && (col != 0 && col != this.gearNumbers[0].size - 1))
                numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row][col-1],
                        this.gearNumbers[row][col+1], this.gearNumbers[row-1][col+1],
                        this.gearNumbers[row-1][col-1])
        }

        fun firstOrLastColumn() {
            if (col == 0 && numbers.isEmpty())
               numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row+1][col], 
                       this.gearNumbers[row][col+1], this.gearNumbers[row-1][col+1], 
                       this.gearNumbers[row+1][col+1])
            else if (col == this.gearNumbers[0].size - 1 && (row != 0 && row !=this.gearNumbers.size - 1))
               numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row+1][col], 
                       this.gearNumbers[row][col-1], this.gearNumbers[row-1][col-1], 
                       this.gearNumbers[row+1][col-1])
        }

        corners()
        firstOrLastRow()
        firstOrLastColumn()
        if (numbers.isEmpty())
            numbers = mutableListOf(this.gearNumbers[row-1][col], this.gearNumbers[row+1][col],
                    this.gearNumbers[row][col-1], this.gearNumbers[row][col+1],
                    this.gearNumbers[row-1][col+1], this.gearNumbers[row-1][col-1],
                    this.gearNumbers[row+1][col+1], this.gearNumbers[row+1][col-1])

        for (num in numbers) {
            if (num != 0)
                product = product.multiply(BigInteger(num.toString()))
        }
        return product
    }

    fun findPartNumberSum():Int {
        var sum = 0
        for (row in this.schematic.indices) {
            sum += getRowSum(row)
        }
        return sum
    }

    private fun getRowSum(row: Int): Int {
        var isPartNumber = false
        var sum = 0
        var gearRowCol = listOf(-1, -1)
        var number = ""
        for (col in this.schematic[row].indices) {
            val ch = this.schematic[row][col]
            if (ch.isDigit()) {
                number += ch.toString()
                if (!isPartNumber) {
                    isPartNumber = checkForSymbols(row, col, "[^.0-9]".toRegex(), false, this.schematic)
                    if (checkForSymbols(row, col, "[*]".toRegex(), false, this.schematic)) {
                        this.gears[row][col] = '1'
                        gearRowCol = listOf(row, col)
                    }
                }
                if (col == this.schematic[row].size - 1 && isPartNumber) {
                    if (!gearRowCol.equals(listOf(-1, -1)))
                        this.gearNumbers[gearRowCol[0]][gearRowCol[1]] = number.toInt()
                    sum += number.toInt()
                    gearRowCol = listOf(-1, -1)
                }
            } else if (number != "" && isPartNumber) {
                if (!gearRowCol.equals(listOf(-1, -1)))
                    this.gearNumbers[gearRowCol[0]][gearRowCol[1]] = number.toInt()
                sum += number.toInt()
                number = ""
                isPartNumber = false
                gearRowCol = listOf(-1, -1)
            } else {
                number = ""
            }
        }
        return sum
    }

    fun calculateGearProduct(): BigInteger {
        var sum = BigInteger.ZERO
        for (row in this.schematic.indices) {
            for (col in this.schematic[row].indices) {
                if (this.schematic[row][col] == '*' &&
                    checkForSymbols(row, col, "[*]".toRegex(), true, this.gears)) {
                    sum = sum.add(multiplyGears(row, col))
                }
            }
        }
        return sum
    }
}

fun main() {
    val input = Path("src/main/resources/inputDay03.txt").readLines()
    val day3 = AOCDay03(input)
    println(day3.findPartNumberSum())
    println(day3.calculateGearProduct())
}

fun print2DArray(array: Array<IntArray>) {
    for (row in array) {
        for (col in row)
            print(col.toString() + " ")
        println()
    }
}

fun print2DArray(array: Array<CharArray>) {
    for (row in array) {
        for (col in row)
            print(col.toString() + " ")
        println()
    }
}