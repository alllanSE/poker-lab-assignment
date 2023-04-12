import org.example.enums.PokerHandRanking;
import org.example.models.PokerHand;
import org.junit.Test;
import util.PokerHandInstanceUtil;

import static org.junit.Assert.assertEquals;
import static org.example.enums.PokerHandRanking.*;

import java.util.List;

public class PokerHandRankingsTest {

    @Test
    public void givenRoyalFlushList_whenGetPokerHandRanking_validateRoyalFlush() {
        List<PokerHand> pokerHandsWithRoyalFlush = PokerHandInstanceUtil.createRoyalFlushes();
        assertPokerHandsRanking(ROYAL_FLUSH, pokerHandsWithRoyalFlush);
    }

    @Test
    public void givenStraightFlushes_whenGetPokerHandRanking_validateStraightFlushes() {
        List<PokerHand> pokerHandsWithStraightFlushes = PokerHandInstanceUtil.createStraightsFlushes();
        assertPokerHandsRanking(STRAIGHT_FLUSH, pokerHandsWithStraightFlushes);
    }

    @Test
    public void givenFourOfAKinds_whenGetPokerHandRanking_validateFourOfAKind() {
        List<PokerHand> fourOfAKinds = PokerHandInstanceUtil.createFourOfAKinds();
        assertPokerHandsRanking(FOUR_OF_A_KIND, fourOfAKinds);
    }

    @Test
    public void givenFullHouses_whenGetPokerHandRanking_validateFullHouse() {
        List<PokerHand> fullHouses = PokerHandInstanceUtil.createFullHouses();
        assertPokerHandsRanking(FULL_HOUSE, fullHouses);
    }

    @Test
    public void givenFlushes_whenGetPokerHandRanking_validateFlush() {
        List<PokerHand> flushes = PokerHandInstanceUtil.createFlushes();
        assertPokerHandsRanking(FLUSH, flushes);
    }

    @Test
    public void givenStraights_whenGetPokerHandRanking_validateStraight() {
        List<PokerHand> straights = PokerHandInstanceUtil.createStraights();
        assertPokerHandsRanking(STRAIGHT, straights);
    }

    @Test
    public void givenThreeOfAKinds_whenGetPokerHandRanking_validateThreeOfAKind() {
        List<PokerHand> threeOfAKinds = PokerHandInstanceUtil.createThreeOfAKinds();
        assertPokerHandsRanking(THREE_OF_A_KIND, threeOfAKinds);
    }

    @Test
    public void givenTwoPairs_whenGetPokerHandRanking_validateTwoPairs() {
        List<PokerHand> twoPairs = PokerHandInstanceUtil.createTwoPairs();
        assertPokerHandsRanking(TWO_PAIR, twoPairs);
    }

    @Test
    public void givenPairs_whenGetPokerHandRanking_validatePair() {
        List<PokerHand> pairs = PokerHandInstanceUtil.createPairs();
        assertPokerHandsRanking(PAIR, pairs);
    }

    private void assertPokerHandsRanking(PokerHandRanking pokerHandRanking, List<PokerHand> pokerHands) {
        for(PokerHand pokerHand: pokerHands) {
            assertEquals(pokerHandRanking, pokerHand.getHandRankingContainer().getPokerHandRanking());
        }
    }
}
