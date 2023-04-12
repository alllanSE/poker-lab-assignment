import org.example.models.PokerHand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class HandRankingContainerTest {

    @Test
    public void givenEqualHandRankingContainers_equal_returnTrue() {
        PokerHand pokerHand1 = PokerHand.of("2S 3S 4S 5S 6S");
        PokerHand pokerHand2 = PokerHand.of("2S 3S 4S 5S 6S");
        assertEquals(pokerHand1.getHandRankingContainer(), pokerHand2.getHandRankingContainer());
    }

    @Test
    public void givenNotEqualHandRankingContainers_equal_returnTrue() {
        PokerHand pokerHand1 = PokerHand.of("2S 3S 5S JD 6S");
        PokerHand pokerHand2 = PokerHand.of("2S 3S 4S 5S 6S");
        assertNotSame(pokerHand1.getHandRankingContainer(), pokerHand2.getHandRankingContainer());
    }
}
