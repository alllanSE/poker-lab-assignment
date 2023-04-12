package org.example.models;


import org.example.utils.CardUtils;
import org.example.utils.HandComparisonUtil;
import org.example.utils.PokerHandUtils;
import org.example.utils.RankingUtil;
import org.example.validators.PokerHandValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokerHand implements Comparable<PokerHand> {

    private List<Card> hand;

    private HandRankingContainer handRankingContainer;

    private PokerHand() {}

    public void setHand(List<Card> hand) {
        initPokerHandFieldsWithValidation(this, hand);
    }

    public List<Card> getHand() {
        return new ArrayList<>(CardUtils.createCardsClone(hand));
    }

    public HandRankingContainer getHandRankingContainer() {
        return this.handRankingContainer.clone();
    }

    public static PokerHand of(String hand) {
        List<Card> cards = PokerHandUtils.mapHandToCards(hand);
        return initPokerHandFieldsWithValidation(new PokerHand(), cards);
    }

    public static PokerHand of(List<Card> cards) {
        return initPokerHandFieldsWithValidation(new PokerHand(), cards);
    }

    private static PokerHand initPokerHandFieldsWithValidation(PokerHand pokerHand, List<Card> cards) {
        PokerHandValidator.processCardsValidation(cards);
        pokerHand.hand = cards;
        pokerHand.handRankingContainer = RankingUtil.getHandRankingContainer(cards);
        return pokerHand;
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        return HandComparisonUtil.comparePokerHands(this, pokerHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equals(hand, pokerHand.hand) && Objects.equals(handRankingContainer, pokerHand.handRankingContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, handRankingContainer);
    }
}
