package util;

import org.example.models.PokerHand;

import java.util.Arrays;
import java.util.List;

public class PokerHandInstanceUtil {
    public static List<PokerHand> createRoyalFlushes() {
        PokerHand royalFlushPokerHand1 = PokerHand.of("TD JD QD KD AD");
        PokerHand royalFlushPokerHand2 = PokerHand.of("TS JS QS KS AS");
        PokerHand royalFlushPokerHand3 = PokerHand.of("TC JC QC KC AC");
        PokerHand royalFlushPokerHand4 = PokerHand.of("TD JD QD KD AD");
        return createListOfHands(royalFlushPokerHand1, royalFlushPokerHand2, royalFlushPokerHand3, royalFlushPokerHand4);
    }

    public static List<PokerHand> createStraightsFlushes() {
        PokerHand straightFlush1 = PokerHand.of("7S, 8S, 9S, TS, JS");
        PokerHand straightFlush2 = PokerHand.of("2C 3C 4C 5C 6C");
        return createListOfHands(straightFlush1, straightFlush2);
    }

    public static List<PokerHand> createFourOfAKinds() {
        PokerHand pokerHand1 = PokerHand.of("4S 4H 4D 4C 5S");
        PokerHand pokerHand2 = PokerHand.of("AS AH AD AC JS");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createFullHouses() {
        PokerHand pokerHand1 = PokerHand.of("4S 4H 4D 5C 5S");
        PokerHand pokerHand2 = PokerHand.of("AS AH AD 2S 2H");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createFlushes() {
        PokerHand pokerHand1 = PokerHand.of("4S 5S JS 2S AS");
        PokerHand pokerHand2 = PokerHand.of("5H AH KH 2H TH");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createStraights() {
        PokerHand pokerHand1 = PokerHand.of("2S 3H 4S 5H 6D");
        PokerHand pokerHand2 = PokerHand.of("7H 8D 9S TS JH");
        return createListOfHands(pokerHand1, pokerHand2);

    }

    public static List<PokerHand> createThreeOfAKinds() {
        PokerHand pokerHand1 = PokerHand.of("2S 2H 2D AH KD");
        PokerHand pokerHand2 = PokerHand.of("TH TD TS 2S 5H");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createTwoPairs() {
        PokerHand pokerHand1 = PokerHand.of("2S 2H 3S 3H AD");
        PokerHand pokerHand2 = PokerHand.of("5H 5D AS AH 2H");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createPairs() {
        PokerHand pokerHand1 = PokerHand.of("2S 2H 3S JH AD");
        PokerHand pokerHand2 = PokerHand.of("5H 5D AS TH 7H");
        return createListOfHands(pokerHand1, pokerHand2);
    }

    public static List<PokerHand> createListOfHands(PokerHand... pokerHands) {
        return Arrays.asList(pokerHands);
    }


}
