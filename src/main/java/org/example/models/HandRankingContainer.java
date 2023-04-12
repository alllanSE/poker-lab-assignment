package org.example.models;

import org.example.enums.PokerHandRanking;
import org.example.utils.CardUtils;

import java.util.List;
import java.util.Objects;

public class HandRankingContainer implements Cloneable {
    private List<Card> cardsCombination;
    private PokerHandRanking pokerHandRanking;
    private List<Card> highCards;

    private HandRankingContainer(){}

    public HandRankingContainer(PokerHandRanking pokerHandRanking, List<Card> cardsCombination, List<Card> highCards) {
        this.cardsCombination = cardsCombination;
        this.pokerHandRanking = pokerHandRanking;
        this.highCards = highCards;
    }

    public List<Card> getCardsCombination() {
        return CardUtils.createCardsClone(cardsCombination);
    }

    public PokerHandRanking getPokerHandRanking() {
        return pokerHandRanking;
    }

    public List<Card> getHighCards() {
        return CardUtils.createCardsClone(highCards);
    }

    @Override
    public HandRankingContainer clone() {
        try {
            HandRankingContainer clone = (HandRankingContainer) super.clone();
            clone.highCards = CardUtils.createCardsClone(this.highCards);
            clone.cardsCombination = CardUtils.createCardsClone(this.cardsCombination);
            clone.pokerHandRanking = this.pokerHandRanking;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandRankingContainer that = (HandRankingContainer) o;
        return Objects.equals(cardsCombination, that.cardsCombination) && pokerHandRanking.equals(that.pokerHandRanking)
                && Objects.equals(highCards, that.highCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardsCombination, pokerHandRanking, highCards);
    }
}
