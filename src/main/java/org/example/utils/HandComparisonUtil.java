package org.example.utils;

import org.example.enums.PokerHandRanking;
import org.example.models.Card;
import org.example.models.HandRankingContainer;
import org.example.models.PokerHand;

import java.util.List;

public class HandComparisonUtil {

    public static int comparePokerHands(PokerHand pokerHand1, PokerHand pokerHand2) {

        HandRankingContainer container1 = pokerHand1.getHandRankingContainer();
        HandRankingContainer container2 = pokerHand2.getHandRankingContainer();

        int pokerHandRankingsComparisonScore = comparePokerHandRankings(container1.getPokerHandRanking(), container2.getPokerHandRanking());
        if(pokerHandRankingsComparisonScore != 0) {
            return pokerHandRankingsComparisonScore;
        }

        int pokerHandRankingComparisonScoreForHandsWithTheSameRanking = comparePokerHandRankingContainerIfSameRanking(container1, container2);
        if(pokerHandRankingComparisonScoreForHandsWithTheSameRanking  != 0 ) {
            return pokerHandRankingComparisonScoreForHandsWithTheSameRanking;
        }

        return compareHighCardsInTwoHands(container1.getHighCards(), container2.getHighCards());
    }

    private static int comparePokerHandRankings(PokerHandRanking pokerHandRanking1, PokerHandRanking pokerHandRanking2) {
        int firstPokerHandRankingScore = pokerHandRanking1.ordinal();
        int secondPokerHandRankingScore = pokerHandRanking2.ordinal();
        return compareScores(firstPokerHandRankingScore, secondPokerHandRankingScore);
    }

    private static int comparePokerHandRankingContainerIfSameRanking(HandRankingContainer container1, HandRankingContainer container2) {
        if(!container1.getPokerHandRanking().equals(container2.getPokerHandRanking())) {
            throw new IllegalArgumentException("Method assumes that hands have the same poker hand rankings!");
        }

        List<Card> firstHandCardsCombination = container1.getCardsCombination();
        List<Card> secondHandCardsCombination = container2.getCardsCombination();

        int firstHandCardsCombinationScore = calculateCardsCombinationScore(firstHandCardsCombination);
        int secondHandCardsCombinationScore = calculateCardsCombinationScore(secondHandCardsCombination);

        return compareScores(firstHandCardsCombinationScore, secondHandCardsCombinationScore);
    }

    private static int compareScores(int score1, int score2){
        if(score1 > score2) {
            return -1;
        } else if(score1 < score2) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int calculateCardsCombinationScore(List<Card> cardsCombination) {
        return cardsCombination.stream()
                .mapToInt(Card::getRankToInt)
                .sum();
    }

    private static int compareHighCardsInTwoHands(List<Card> oneHandHighCards, List<Card> anotherHandHighCards) {
        for(int i = 0; i < oneHandHighCards.size(); i++) {
            Card firstHandCard = oneHandHighCards.get(i);
            Card secondHandCard = anotherHandHighCards.get(i);
            int score = compareScores(firstHandCard.getRankToInt(), secondHandCard.getRankToInt());
            if(score != 0) {
                return score;
            }
        }

        return 0;
    }
}
