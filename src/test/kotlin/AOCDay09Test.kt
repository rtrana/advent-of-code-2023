import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay09Test {

    @Test
    fun `should return extrapolated value of 28 for list of (1, 3, 6, 10, 15, 21)`() {
        assertEquals(28, AOCDay09().getExtrapolatedValue(listOf(1, 3, 6, 10, 15, 21)))
    }

    @Test
    fun `should return extrapolated value of 68 for list of (10, 13, 16, 21, 30, 45)`() {
        assertEquals(68, AOCDay09().getExtrapolatedValue(listOf(10, 13, 16, 21, 30, 45)))
    }

    @Test
    fun `should return extrapolated value of 18 for list of (0, 3, 6, 9, 12, 15)`() {
        assertEquals(18, AOCDay09().getExtrapolatedValue(listOf(0, 3, 6, 9, 12, 15)))
    }

    @Test
    fun `should return sum of extrapolated values`() {
        val report: MutableList<MutableList<Long>> = mutableListOf(
            mutableListOf(0, 3, 6, 9, 12, 15),
            mutableListOf(1, 3, 6, 10, 15, 21),
            mutableListOf(10, 13, 16, 21, 30, 45)
        )
        assertEquals(114, AOCDay09().addExtrapolatedValues(report))
    }
}