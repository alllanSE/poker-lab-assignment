package org.example.utils;

import org.example.enums.PokerHandRanking;
import org.example.models.Card;
import org.example.models.HandRankingContainer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.enums.CardRank.*;
import static org.example.enums.PokerHandRanking.*;

public class RankingUtil {

    private static Optional<HandRankingContainer> initHandRankingContainer(PokerHandRanking pokerHandRanking, List<Card> hand, List<Card> cardsCombination) {
        if(cardsCombination.size() != 0) {
            List<Card> highCards = findHighestCardsGivenPokerHandRanking(pokerHandRanking, hand, cardsCombination);
            return Optional.of(new HandRankingContainer(pokerHandRanking, cardsCombination, highCards));
        }
        return Optional.empty();
    }

    public static HandRankingContainer getHandRankingContainer(List<Card> hand) {
        Optional<HandRankingContainer> handRankingContainerOptional;

        List<Card> royalFlush = getRoyalFlush(hand);
        handRankingContainerOptional = initHandRankingContainer(ROYAL_FLUSH, hand, royalFlush);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> straightFlush = getStraightFlush(hand);
        handRankingContainerOptional = initHandRankingContainer(STRAIGHT_FLUSH, hand, straightFlush);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> fourOfAKind = getFourOfAKind(hand);
        handRankingContainerOptional = initHandRankingContainer(FOUR_OF_A_KIND, hand, fourOfAKind);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> fullHouse = getFullHouse(hand);
        handRankingContainerOptional = initHandRankingContainer(FULL_HOUSE, hand, fullHouse);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> flush = getFlush(hand);
        handRankingContainerOptional = initHandRankingContainer(FLUSH, hand, flush);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> straight = getStraight(hand);
        handRankingContainerOptional = initHandRankingContainer(STRAIGHT, hand, straight);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> threeOfAKind = getThreeOfAKind(hand);
        handRankingContainerOptional = initHandRankingContainer(THREE_OF_A_KIND, hand, threeOfAKind);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> twoPair = getTwoPair(hand);
        handRankingContainerOptional = initHandRankingContainer(TWO_PAIR, hand, twoPair);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> pair = getPair(hand);
        handRankingContainerOptional = initHandRankingContainer(PAIR, hand, pair);
        if(handRankingContainerOptional.isPresent()) {
            return handRankingContainerOptional.get();
        }

        List<Card> highCards = findHighestCardsGivenPokerHandRanking(HIGH_CARD, hand, new ArrayList<>());
        return new HandRankingContainer(HIGH_CARD, new ArrayList<>(), highCards);
    }

    public static List<Card> getRoyalFlush(List<Card> cards) {
        List<Card> copyOfCards = new ArrayList<>(cards);
        copyOfCards = sortCardsByRank(copyOfCards);
        List<Card> royalFlush = getStraightFlush(copyOfCards);
        if(royalFlush.size() != 0 && royalFlush.get(0).getRank().equals(TEN)){
            return royalFlush;
        }
        return new ArrayList<>();
    }

    public static List<Card> getStraightFlush(List<Card> cards) {
        boolean sameSuit = isSameSuit(cards);
        if(!sameSuit) {
            return new ArrayList<>();
        }
        return getStraight(cards);
    }

    private static boolean checkIfSortedByRankCardsFormASequence(List<Card> cards) {
        return IntStream.range(0, cards.size() - 1)
                .allMatch(i -> cards.get(i + 1).getRankToInt() - cards.get(i).getRankToInt() == 1);
    }

    public static List<Card> getFourOfAKind(List<Card> cards) {
        List<Card> fourOfAKind = getNCardsWithIdenticalRank(cards, 4);
        if(fourOfAKind.size() == 4) {
            return fourOfAKind;
        }
        return new ArrayList<>();
    }

    public static List<Card> getFullHouse(List<Card> cards) {
        List<Card> copyOfCards = new ArrayList<>(cards);
        List<Card> threeCardsWithIdenticalRank = getNCardsWithIdenticalRank(copyOfCards, 3);
        copyOfCards.removeAll(threeCardsWithIdenticalRank);
        List<Card> twoCardsWithIdenticalRank = getNCardsWithIdenticalRank(copyOfCards, 2);
        if(threeCardsWithIdenticalRank.size() == 3 && twoCardsWithIdenticalRank.size() == 2) {
            return mergeTwoHands(threeCardsWithIdenticalRank, twoCardsWithIdenticalRank);
        }
        return new ArrayList<>();
    }

    public static List<Card> getFlush(List<Card> cards) {
        if(isSameSuit(cards)) {
            return cards;
        }
        return new ArrayList<>();
    }

    public static List<Card> getStraight(List<Card> cards) {
        List<Card> sortedCards = sortCardsByRank(cards);
        boolean isSortedByRankCardsFormASequence = checkIfSortedByRankCardsFormASequence(new ArrayList<>(sortedCards));
        if(isSortedByRankCardsFormASequence) {
            return sortedCards;
        }
        return new ArrayList<>();
    }

    public static List<Card> getThreeOfAKind(List<Card> cards) {
        List<Card> threeCardsWithIdenticalRank = getNCardsWithIdenticalRank(cards, 3);
        if(threeCardsWithIdenticalRank.size() == 3) {
            return threeCardsWithIdenticalRank;
        }
        return new ArrayList<>();
    }

    public static List<Card> getTwoPair(List<Card> cards) {
        List<Card> copyOfCards = new ArrayList<>(cards);
        List<Card> pair = getPair(copyOfCards);

        if(pair.size() != 2) {
            return new ArrayList<>();
        }

        copyOfCards.removeAll(pair);

        List<Card> anotherPair = getPair(copyOfCards);
        if(anotherPair.size() != 2) {
            return  new ArrayList<>();
        }

        return mergeTwoHands(pair, anotherPair);
    }

    public static List<Card> getPair(List<Card> cards) {
        List<Card> copyOfCards = new ArrayList<>(cards);
        List<Card> twoCardsWithIdenticalRank = getNCardsWithIdenticalRank(copyOfCards, 2);
        if(twoCardsWithIdenticalRank.size() == 2){
            return twoCardsWithIdenticalRank;
        }
        return new ArrayList<>();
    }

    private static List<Card> mergeTwoHands(List<Card> hand1, List<Card> hand2) {
        List<Card> merged = new ArrayList<>();
        merged.addAll(hand1);
        merged.addAll(hand2);
        return merged;
    }

    private static List<Card> getNCardsWithIdenticalRank(List<Card> cards, int n) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getRank))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == n)
                .flatMap(entry -> entry.getValue().stream())
                .limit(n)
                .collect(Collectors.toList());
    }

    private static boolean isSameSuit(List<Card> cards) {
        return cards.stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1 ;
    }

    private static List<Card> sortCardsByRank(List<Card> cards) {
        return cards.stream()
                .sorted(Comparator.comparingInt(Card::getRankToInt))
                .collect(Collectors.toList());
    }

    public static List<Card> findHighestCardsGivenPokerHandRanking(PokerHandRanking pokerHandRanking, List<Card> hand, List<Card> handCombination) {
        List<Card> copyOfHand = new ArrayList<>(hand);
        if(checkIfFindingHighCardsNeedRemovalOfSomeCards(pokerHandRanking)) {
            copyOfHand.removeAll(handCombination);
        }
        return getHighCardsFromLeftCards(copyOfHand);
    }

    private static List<Card> getHighCardsFromLeftCards(List<Card> cards) {
        return cards.stream()
                .sorted(Comparator.comparing(Card::getRankToInt).reversed())
                .collect(Collectors.toList());
    }

    private static boolean checkIfFindingHighCardsNeedRemovalOfSomeCards(PokerHandRanking pokerHandRanking) {
        return pokerHandRanking.equals(PAIR) || pokerHandRanking.equals(TWO_PAIR) || pokerHandRanking.equals(THREE_OF_A_KIND)
                || pokerHandRanking.equals(FOUR_OF_A_KIND);
    }
}
