package org.example.utils;

import org.example.enums.CardRank;
import org.example.enums.CardSuit;
import org.example.models.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandUtils {


    public static List<Card> mapHandToCards(String hand) {
        Collection<String> cards = convertStrHandToCollection(hand);
        return cards.stream()
                .map(card -> {
                        String rank = String.valueOf(card.charAt(0));
                        String suit = String.valueOf(card.charAt(1));
                        CardRank cardRank = CardRank.fromText(rank).get();
                        CardSuit cardSuit = CardSuit.fromText(suit).get();
                        return new Card(cardRank, cardSuit);
                    })
                .collect(Collectors.toList());
    }

    private static List<String> convertStrHandToCollection(String hand){
        String[] splitedHand = hand.split(" ");
        return new ArrayList<>(Arrays.asList(splitedHand));
    }
}
