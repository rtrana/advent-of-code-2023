import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay05Test {

    @Test
    fun `should test seed range falls fully within a mapping range`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(79, 93)), day05.agroMappings[0])
        val expectedConvertedRange = mutableListOf(mutableListOf(81L, 95L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside of left boundary but in right boundary`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(45, 52)), day05.agroMappings[0])
        val expectedConvertedRange = mutableListOf(mutableListOf(45L, 49L), mutableListOf(52L, 54L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside of right boundary but in left boundary`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(55, 67)), day05.agroMappings[2])
        val expectedConvertedRange = mutableListOf(mutableListOf(51L, 56L), mutableListOf(61L, 67L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside of both boundaries`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(55, 67)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(55L, 56L), mutableListOf(14L, 21L),
            mutableListOf(65L, 67L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside of left-most boundary`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(42, 49)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(42L, 49L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside of right-most boundary`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(67, 70)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(67L, 70L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls fully within a mapping range and is length 1`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(57L)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(14L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside the left boundary of a mapping range and is length 1`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(50L)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(50L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

    @Test
    fun `should test seed range falls outside the right boundary of a mapping range and is length 1`() {
        val input = Path("src/main/resources/inputDay05.txt").readLines()
        var day05 = AOCDay05(input)
        day05.parseAgroInfo()
        val agroMapping = mutableListOf(AgroMapping(57L, 14L, 8))
        val convertedRange = day05.convertSeedRangesForAgroMap(mutableListOf(mutableListOf(90L)), agroMapping)
        val expectedConvertedRange = mutableListOf(mutableListOf(90L))
        assertEquals(expectedConvertedRange.size, convertedRange.size)
        assertArrayEquals(expectedConvertedRange.toTypedArray(), convertedRange.toTypedArray())
    }

}