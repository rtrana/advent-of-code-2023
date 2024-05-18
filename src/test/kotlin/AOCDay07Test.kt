import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AOCDay07Test {

    @Test
    fun `should get high card values for hand with no duplicates`() {
        var hand = CamelHand("T8Q31")
        assertEquals(CamelHand.CamelHandType.HIGH_CARD, hand.handType)
    }

    @Test
    fun `should get pair values for hand with 1 set of duplicates`() {
        var hand = CamelHand("596J6")
        assertEquals(CamelHand.CamelHandType.PAIR, hand.handType)
    }

    @Test
    fun `should get two pair values for hand with 2 sets of duplicates`() {
        var hand = CamelHand("553QQ")
        assertEquals(CamelHand.CamelHandType.TWO_PAIR, hand.handType)
    }

    @Test
    fun `should get three of a kind values for hand with triplicates`() {
        var hand = CamelHand("A2A8A")
        assertEquals(CamelHand.CamelHandType.THREE_KIND, hand.handType)
    }

    @Test
    fun `should get full house values for hand with triplicate and duplicate`() {
        var hand = CamelHand("99696")
        assertEquals(CamelHand.CamelHandType.FULL_HOUSE, hand.handType)
    }

    @Test
    fun `should get four of a kind values for hand with quads`() {
        var hand = CamelHand("44434")
        assertEquals(CamelHand.CamelHandType.FOUR_KIND, hand.handType)
    }

    @Test
    fun `should get five of a kind values for hand with quints`() {
        var emptyList: List<Int> = listOf()
        var hand = CamelHand("77777")
        assertEquals(CamelHand.CamelHandType.FIVE_KIND, hand.handType)
    }

    @Test
    fun `should compare three of a kind to four of a kind hands`() {
        var three = CamelHand("78788")
        var four = CamelHand("85555")
        assertEquals(-1, three.compareTo(four))
        assertEquals(1, four.compareTo(three))
    }

    @Test
    fun `should compare two pair to two pair hands`() {
        var first = CamelHand("288TT")
        var second = CamelHand("55442")
        assertEquals(-1, first.compareTo(second))
    }

}