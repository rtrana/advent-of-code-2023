import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay02Test {

    @Test
    fun `getMaxColorsPerGame should find max colors of each game`() {
        val games = listOf(
            "3 blue, 4 red, 1 red, 2 green, 6 blue, 2 green",
            "1 blue, 2 green, 3 green, 4 blue, 1 red, 1 green, 1 blue",
            "8 green, 6 blue, 20 red, 5 blue, 4 red, 13 green, 5 green, 1 red",
            "1 green, 3 red, 6 blue, 3 green, 6 red, 3 green, 15 blue, 14 red",
            "6 red, 1 blue, 3 green, 2 blue, 1 red, 2 green")

        var expectedGameMap: Map<Int, Map<String, Int>> = mapOf(
            1 to mapOf("red" to 4, "green" to 2, "blue" to 6),
            2 to mapOf("red" to 1, "green" to 3, "blue" to 4),
            3 to mapOf("red" to 20, "green" to 13, "blue" to 6),
            4 to mapOf("red" to 14, "green" to 3, "blue" to 15),
            5 to mapOf("red" to 6, "green" to 3, "blue" to 2))

        assertEquals(expectedGameMap, AOCDay02().getMaxColorsPerGame(games))
    }

    @Test
    fun `getPossibleGames should find sum game indices`() {
        val games = listOf(
            "3 blue, 4 red, 1 red, 2 green, 6 blue, 2 green",
            "1 blue, 2 green, 3 green, 4 blue, 1 red, 1 green, 1 blue",
            "8 green, 6 blue, 20 red, 5 blue, 4 red, 13 green, 5 green, 1 red",
            "1 green, 3 red, 6 blue, 3 green, 6 red, 3 green, 15 blue, 14 red",
            "6 red, 1 blue, 3 green, 2 blue, 1 red, 2 green")

        var expectedGameMap: Map<Int, Map<String, Int>> = mapOf(
            1 to mapOf("red" to 4, "green" to 2, "blue" to 6),
            2 to mapOf("red" to 1, "green" to 3, "blue" to 4),
            3 to mapOf("red" to 20, "green" to 13, "blue" to 6),
            4 to mapOf("red" to 14, "green" to 3, "blue" to 15),
            5 to mapOf("red" to 6, "green" to 3, "blue" to 2))

        assertEquals(8, AOCDay02().findPossibleGames(games, 12, 13, 14))
    }

    @Test
    fun `getPowerSum should sum the multiple of the least possible ball combos`() {
        val games = listOf(
            "3 blue, 4 red, 1 red, 2 green, 6 blue, 2 green",
            "1 blue, 2 green, 3 green, 4 blue, 1 red, 1 green, 1 blue",
            "8 green, 6 blue, 20 red, 5 blue, 4 red, 13 green, 5 green, 1 red",
            "1 green, 3 red, 6 blue, 3 green, 6 red, 3 green, 15 blue, 14 red",
            "6 red, 1 blue, 3 green, 2 blue, 1 red, 2 green")

        var expectedGameMap: Map<Int, Map<String, Int>> = mapOf(
            1 to mapOf("red" to 4, "green" to 2, "blue" to 6),
            2 to mapOf("red" to 1, "green" to 3, "blue" to 4),
            3 to mapOf("red" to 20, "green" to 13, "blue" to 6),
            4 to mapOf("red" to 14, "green" to 3, "blue" to 15),
            5 to mapOf("red" to 6, "green" to 3, "blue" to 2))

        assertEquals(2286, AOCDay02().getPower(games))
    }
}