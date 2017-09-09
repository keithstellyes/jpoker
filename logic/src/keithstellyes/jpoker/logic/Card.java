package keithstellyes.jpoker.logic;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Comparator;

// MUST BE IMMUTABLE
public class Card {
    private final CardRank rank;
    private final CardSuit suit;
    private static final Card[] allCards;
    private final String toStringOutput;

    static {
        allCards = new Card[CardRank.values().length * CardSuit.values().length];
        int index = 0;

        for(CardRank rank : CardRank.values()) {
            for(CardSuit suit : CardSuit.values()) {
                allCards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    private Card(CardRank rank, CardSuit suit) {
        this.suit = suit;
        this.rank = rank;
        this.toStringOutput = rank.toString() + " of " + suit.toString();
    }

    public static Card create(CardRank rank, CardSuit suit) {
        int index = suit.ordinal() + (rank.ordinal() * CardSuit.values().length);

        return allCards[index];
    }

    public CardRank getRank() {
        return this.rank;
    }

    public CardSuit getSuit() {
        return this.suit;
    }

    @Override
    public boolean equals(Object other) {
        Card otherCard = (Card) other;
        return rank == otherCard.rank && suit == otherCard.suit;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(rank)
                .append(suit).build();
    }

    public static Card[] values() {
        return allCards.clone();
    }

    public String toString() {
        return toStringOutput;
    }
}
