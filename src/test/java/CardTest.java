import org.example.models.Card;
import org.junit.*;
import static org.example.enums.CardSuit.*;
import static org.example.enums.CardRank.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class CardTest  {

    @Test
    public void givenTwoCardsWithTheSameValues_whenEquals_returnTrue(){
        Card card1 = new Card(TWO, DIAMONDS);
        Card card2 = new Card(TWO, DIAMONDS);
        assertEquals(card1, card2);
    }

    @Test
    public void givenTwoCardsWithSameRanksDifferentSuita_whenEquals_returnFalse(){
        Card card1 = new Card(TWO, DIAMONDS);
        Card card2 = new Card(TWO, SPADES);
        assertNotSame(card1, card2);
    }


}
