import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay06Test {

    @Test
    fun `should calculate number of ways for an even time given minimum boundary`() {
        assertEquals(5, AOCDay06().calculateTotalNumberOfWays(8, 2))
    }

    @Test
    fun `should calculate number of ways for an odd time given minimum boundary`() {
        assertEquals(4, AOCDay06().calculateTotalNumberOfWays(7, 2))
    }

    @Test
    fun `should calculate number of ways for time if minimum boundary is -1`() {
        assertEquals(0, AOCDay06().calculateTotalNumberOfWays(7, -1))
    }

    @Test
    fun `should find minimum boundary for odd number`() {
        assertEquals(2, AOCDay06().findMinimumBoundary(7, 9))
    }

    @Test
    fun `should find minimum boundary for even number`() {
        assertEquals(2, AOCDay06().findMinimumBoundary(8, 9))
    }

    @Test
    fun `should find minimum boundary for use case of 15 with record of 40`() {
        assertEquals(4, AOCDay06().findMinimumBoundary(15, 40))
    }

    @Test
    fun `should find minimum boundary for use case of 30 with record of 200`() {
        assertEquals(11, AOCDay06().findMinimumBoundary(30, 200))
    }

    @Test
    fun `should find minimum boundary of -1 if not possible`() {
        assertEquals(-1, AOCDay06().findMinimumBoundary(7, 14))
    }

}