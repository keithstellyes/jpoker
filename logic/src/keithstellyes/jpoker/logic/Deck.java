package keithstellyes.jpoker.logic;

import java.util.*;

public class Deck {
    private final List<Card> cards;
    public Deck() {
        cards = new ArrayList<>(Arrays.asList(Card.values()));
    }

    public Card draw() {
        Card card = cards.get(0);
        cards.remove(0);

        return card;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Hand drawHand() {
        Hand hand = new Hand();
        for(int i = 0; i < Hand.HAND_SIZE; i++) {
            hand.add(draw());
        }

        return hand;
    }
}
