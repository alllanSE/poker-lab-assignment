package org.example.utils;

import org.example.models.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardUtils {
    public static List<Card> createCardsClone(List<Card> cards) {
        return cards.stream().map(Card::clone).collect(Collectors.toList());
    }
}
