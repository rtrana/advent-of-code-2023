import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay01Test {

    @Test
    fun `part1 should return sum of first-last digits`() {
        val lines = listOf("fjrmmfqv3gnxnjdtkdmk25zghnp", "24")
        assertEquals(59, part1(lines))
    }

    @Test
    fun `part1 should double when only one digit`() {
        val lines = listOf("joneight7ninefive")
        assertEquals(77, part1(lines))
    }

    @Test
    fun `part2 should handle overlapping word numbers`() {
        val lines = listOf("eighthreeighthreeightwo", "joneight7ninefive")
        assertEquals(97, part2(lines))
    }

    @Test
    fun `part2 should double when only one word number`() {
        val lines = listOf("brrrrfourrbrrjqqph")
        assertEquals(44, part2(lines))
    }
}