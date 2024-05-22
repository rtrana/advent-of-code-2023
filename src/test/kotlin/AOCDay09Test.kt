import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay09Test {

    @Test
    fun `should return extrapolated value of 28 for list of (1, 3, 6, 10, 15, 21)`() {
        var expected: List<Long> = listOf(28, 0)
        assertEquals(expected, AOCDay09().getExtrapolatedValues(listOf(1, 3, 6, 10, 15, 21)))
    }

    @Test
    fun `should return extrapolated value of 68 for list of (10, 13, 16, 21, 30, 45)`() {
        val listOf: List<Long> = listOf(68, 5)
        assertEquals(listOf, AOCDay09().getExtrapolatedValues(listOf(10, 13, 16, 21, 30, 45)))
    }

    @Test
    fun `should return extrapolated value of 18 for list of (0, 3, 6, 9, 12, 15)`() {
        val expected: List<Long> = listOf(18, -3)
        assertEquals(expected, AOCDay09().getExtrapolatedValues(listOf(0, 3, 6, 9, 12, 15)))
    }

    @Test
    fun `should return sum of extrapolated values`() {
        val report: MutableList<MutableList<Long>> = mutableListOf(
            mutableListOf(0, 3, 6, 9, 12, 15),
            mutableListOf(1, 3, 6, 10, 15, 21),
            mutableListOf(10, 13, 16, 21, 30, 45)
        )
        var extrapolations: List<Long> = listOf(114, 2)
        assertEquals(extrapolations, AOCDay09().calculateExtrapolatedValues(report))
    }
}