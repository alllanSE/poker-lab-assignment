package org.example.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CardRank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    J("J"),
    Q("Q"),
    K("K"),
    A("A");

    private String text;

    CardRank(String text) {
        this.text = text;
    }

    public static Optional<CardRank> fromText(String text) {
        return Arrays.stream(values())
                .filter(suit -> suit.text.equalsIgnoreCase(text))
                .findFirst();
    }
}
