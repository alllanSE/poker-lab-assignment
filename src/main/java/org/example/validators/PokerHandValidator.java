package org.example.validators;


import org.example.exception.PokerHandInvalidException;
import org.example.models.Card;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHandValidator {

    public static void processCardsValidation(List<Card> cards) {
        validateHandLength(cards);
        checkHandForDuplicates(cards);
    }

    public static void validateHandLength(List<Card> cards) {
        if (cards.size() != 5) {
            throw new PokerHandInvalidException("Hand must consist of 5 cards.");
        }
    }

    public static void checkHandForDuplicates(List<Card> cards) {
        Set<Card> duplicateCards = cards.stream()
                .filter(card -> Collections.frequency(cards, card) > 1)
                .collect(Collectors.toSet());
        if (duplicateCards.size() > 0) {
            throw new PokerHandInvalidException("Hand must not contain duplicates.");
        }
    }
}
