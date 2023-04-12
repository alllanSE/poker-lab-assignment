package org.example.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CardSuit {
    HEARTS("H"),
    DIAMONDS("D"),
    SPADES("S"),
    CLUBS("C");

    private String text;

    CardSuit(String text) {
        this.text = text;
    }

    public static Optional<CardSuit> fromText(String text) {
        return Arrays.stream(values())
                .filter(suit -> suit.text.equalsIgnoreCase(text))
                .findFirst();
    }
}
