import org.example.models.PokerHand;
import org.junit.Test;
import util.PokerHandInstanceUtil;

import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.example.enums.PokerHandRanking.THREE_OF_A_KIND;
public class PokerHandsComparisonTests {

    @Test
    public void givenHands_whenSort_getOrderOfPairAndTwoPairsAndFlushAndRoyalFlush(){
        PokerHand pair = PokerHand.of("5S 5D JD AS TH");
        PokerHand twoPair = PokerHand.of("5S 5D TD TH AH");
        PokerHand threeOfAKind = PokerHand.of("5S 5H 5D TC AC");
        PokerHand straight = PokerHand.of("2S 3H 4S 5D 6S");
        PokerHand flush = PokerHand.of("JS 2S 7S TS AS");
        PokerHand fullHouse = PokerHand.of("TH TS TD 2H 2S");
        PokerHand fourOfAKind = PokerHand.of("JS JH JC JD 2H");
        PokerHand straightFlush = PokerHand.of("5S 6S 7S 8S 9S");
        PokerHand royalFlush = PokerHand.of("TH JH QH KH AH");
        PokerHand handWithOnlyHighCard = PokerHand.of("KH 2S 5H 8D TS");

        List<PokerHand> pokerHands = PokerHandInstanceUtil.createListOfHands(pair, twoPair, flush, royalFlush, threeOfAKind, straightFlush, straight, fullHouse, fourOfAKind, handWithOnlyHighCard);
        Collections.shuffle(pokerHands);

        Collections.sort(pokerHands);
        assertEquals(handWithOnlyHighCard, pokerHands.get(9));
        assertEquals(pair, pokerHands.get(8));
        assertEquals(twoPair, pokerHands.get(7));
        assertEquals(threeOfAKind, pokerHands.get(6));
        assertEquals(straight, pokerHands.get(5));
        assertEquals(flush, pokerHands.get(4));
        assertEquals(fullHouse, pokerHands.get(3));
        assertEquals(fourOfAKind, pokerHands.get(2));
        assertEquals(straightFlush, pokerHands.get(1));
        assertEquals(royalFlush, pokerHands.get(0));
    }

    @Test
    public void testOrderWhenSomeHandsHaveTheSameRankingAndSomeSomeRankingCombination() {

        PokerHand weekStraightFlush = PokerHand.of("5S 6S 7S 8S 9S");
        PokerHand strongStraightFlush = PokerHand.of("7S 8S 9S TS JS");

        PokerHand fourOfAKindWithStrongHighCard = PokerHand.of("5S 5D 5H 5C JC");
        PokerHand fourOfAKindWithWeekHighCard = PokerHand.of("5S 5D 5H 5C TD");

        PokerHand weekStraight = PokerHand.of("5S 6H 7D 8D 9H");
        PokerHand strongStraight = PokerHand.of("9D TS JC QC KS");

        PokerHand threeOfAKindWithStrongHighCard = PokerHand.of("3S 3D 3C TD AD");
        PokerHand sameThreeOfAKindWithWeekHighCard = PokerHand.of("3S 3D 3C 8D 7C");

        PokerHand weekPair = PokerHand.of("2D 2S 6S TD JS");
        PokerHand strongPair = PokerHand.of("AS AD 5S 9H JS");

        PokerHand strongHighCard = PokerHand.of("2C 5D 8S QS TD");
        PokerHand weekHighCard = PokerHand.of("5S 8D 9C TD 2C");

        List<PokerHand> pokerHands =
        PokerHandInstanceUtil.createListOfHands(weekStraightFlush, strongStraightFlush,
                                                fourOfAKindWithStrongHighCard, fourOfAKindWithWeekHighCard,
                                                weekStraight, strongStraight,
                                                threeOfAKindWithStrongHighCard, sameThreeOfAKindWithWeekHighCard,
                                                weekPair, strongPair,
                                                strongHighCard, weekHighCard);

        Collections.shuffle(pokerHands);
        Collections.sort(pokerHands);

        assertEquals(strongStraightFlush, pokerHands.get(0));
        assertEquals(weekStraightFlush, pokerHands.get(1));

        assertEquals(fourOfAKindWithStrongHighCard, pokerHands.get(2));
        assertEquals(fourOfAKindWithWeekHighCard, pokerHands.get(3));

        assertEquals(strongStraight, pokerHands.get(4));
        assertEquals(weekStraight, pokerHands.get(5));

        assertEquals(threeOfAKindWithStrongHighCard, pokerHands.get(6));
        assertEquals(sameThreeOfAKindWithWeekHighCard, pokerHands.get(7));

        assertEquals(strongPair, pokerHands.get(8));
        assertEquals(weekPair, pokerHands.get(9));

        assertEquals(strongHighCard, pokerHands.get(10));
        assertEquals(weekHighCard, pokerHands.get(11));
    }

    @Test
    public void givenEqualThreeThreeOfAkinds_sort_handWithhighestCardComesFirst() {
        PokerHand strongestHighCardPokerHand = PokerHand.of("3C 3D 3H AC TH");
        PokerHand middleHighestCardPokerHand = PokerHand.of("3C 3D 3H 7D 9S");
        PokerHand smallestHighestCardPOkerHand = PokerHand.of("3C 3D 3H 7D 9S");

        List<PokerHand> pokerHands = PokerHandInstanceUtil.createListOfHands(strongestHighCardPokerHand, middleHighestCardPokerHand, smallestHighestCardPOkerHand);
        Collections.shuffle(pokerHands);
        Collections.sort(pokerHands);

        assertEquals(THREE_OF_A_KIND, strongestHighCardPokerHand.getHandRankingContainer().getPokerHandRanking());
        assertEquals(THREE_OF_A_KIND, middleHighestCardPokerHand.getHandRankingContainer().getPokerHandRanking());
        assertEquals(THREE_OF_A_KIND, smallestHighestCardPOkerHand.getHandRankingContainer().getPokerHandRanking());

        assertEquals(pokerHands.get(0), strongestHighCardPokerHand);
        assertEquals(pokerHands.get(1), middleHighestCardPokerHand);
        assertEquals(pokerHands.get(2), smallestHighestCardPOkerHand);


    }
}
