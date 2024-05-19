import java.util.function.Predicate

class CamelHand(var handName: String): Comparable<CamelHand> {

    var letterMapper: Map<String, Int> = mapOf(
        Pair("2", 2), Pair("3", 3), Pair("4", 4), Pair("5", 5),
        Pair("6", 6), Pair("7", 7), Pair("8", 8), Pair("9", 9),
        Pair("T", 10), Pair("J", 1), Pair("Q", 12), Pair("K", 13), Pair("A", 14) )

    var countMap = convertHandToMappedIntegers(this.handName)

    var handType: CamelHandType? = CamelHandType.find(this.countMap.values.toList())

    fun convertHandToMappedIntegers(hand: String): Map<Int, Int> {
        var originalHandMap = hand.toList()
            .map { it ->  it.toString()}
            .map { it -> letterMapper.getOrDefault(it, 0) }
            .groupingBy { it }.eachCount()
            .toList().sortedWith(compareBy({ it.second }, { it.first })).asReversed().toMap().toMutableMap()

        if (originalHandMap.contains(1)) {
            var jokerCount = originalHandMap.getOrDefault(1, 0)
            originalHandMap.remove(1)
            if (jokerCount == 5)
                originalHandMap[13] = 5
            else {
                var keys = originalHandMap.keys.toList()
                var maxValue: Int = keys[0]
                originalHandMap[maxValue] = originalHandMap.getOrDefault(maxValue, 0) + jokerCount
            }
        }
        return originalHandMap
    }

    override fun equals(other: Any?): Boolean {
        if (other !is CamelHand)
            return false
        return this.handType == other.handType
    }

    override fun toString(): String {
        return this.handName + " " + this.handType
    }

    override fun compareTo(other: CamelHand): Int {
        if (this.handType!!.ordinal < other.handType!!.ordinal)
            return -1
        else if (this.handType!!.ordinal > other.handType!!.ordinal)
            return 1

        for (i in this.handName.indices) {
            var thisValue: String = this.handName[i].toString()
            var otherValue: String = other.handName[i].toString()
            if (letterMapper[thisValue]!! <  letterMapper[otherValue]!!)
                return -1
            else if (letterMapper[thisValue]!! >  letterMapper[otherValue]!!)
                return 1
        }

        return 0
    }

    enum class CamelHandType: Predicate<List<Int>> {
        HIGH_CARD {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(1, 1, 1, 1, 1)
            }
        },
        PAIR {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(2, 1, 1, 1)
            }
        },
        TWO_PAIR {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(2, 2, 1)
            }
        },
        THREE_KIND {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(3, 1, 1)
            }
        },
        FULL_HOUSE {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(3, 2)
            }
        },
        FOUR_KIND {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(4, 1)
            }
        },
        FIVE_KIND {
            override fun test(counts: List<Int>): Boolean {
                return counts == listOf(5)
            }
        };

        companion object {
            fun find(counts: List<Int>): CamelHandType? = entries.first() { it.test(counts) }
        }
    }
}