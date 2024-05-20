import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay08Test {

    @Test
    fun `should find count for nodes without direction repetition`() {
        val input = listOf("RL", "" , "AAA = (BBB, CCC)", "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)", "DDD = (DDD, DDD)", "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)", "ZZZ = (ZZZ, ZZZ)")
        val construct = AOCDay08(input)
        assertEquals(2, construct.countNodeStops("AAA", "ZZZ"))
    }

    @Test
    fun `should find count for nodes with direction repetition`() {
        val input = listOf("LLR", "", "AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)")
        val construct = AOCDay08(input)
        assertEquals(6, construct.countNodeStops("AAA", "ZZZ"))
    }

    @Test
    fun `should get all the nodes that end with A`() {
        val input = listOf("LR", "", "11A = (11B, XXX)", "11B = (XXX, 11Z)", "11Z = (11B, XXX)",
            "22A = (22B, XXX)", "22B = (22C, 22C)", "22C = (22Z, 22Z)", "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)")
        assertEquals(listOf("11A", "22A"), AOCDay08(input).getStartingNodes())
    }

    @Test
    fun `should get count to get to all nodes ending in Z`() {
        val input = listOf("LR", "", "11A = (11B, XXX)", "11B = (XXX, 11Z)", "11Z = (11B, XXX)",
            "22A = (22B, XXX)", "22B = (22C, 22C)", "22C = (22Z, 22Z)", "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)")
        assertEquals(6, AOCDay08(input).getCountToAllEndingNodes())
    }
}