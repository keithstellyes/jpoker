package keithstellyes.jpoker.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand extends ArrayList<Card> {
    public static final int HAND_SIZE = 5;

    public Hand() {

    }

    public Hand(List<Card> cards) {
        if(cards.size() != HAND_SIZE) {
            throw new IllegalArgumentException("Hand size must be equal to:" + HAND_SIZE);
        }

        addAll(cards);
    }

    public Hand(Hand hand) {
        addAll(hand);
    }

    public boolean add(Card card) {
        if(size() < HAND_SIZE) {
            return super.add(card);
        } else {
            throw new IndexOutOfBoundsException("Hand size cannot exceed:" + HAND_SIZE);
        }
    }

    public CardRank[] getRanks() {
        Set<CardRank> cardRankSet = new HashSet<>();
        for(Card card : this) {
            cardRankSet.add(card.getRank());
        }

        return cardRankSet.toArray(new CardRank[cardRankSet.size()]);
    }

    public CardSuit[] getSuits() {
        Set<CardSuit> cardSuitSet = new HashSet<>();
        for(Card card : this) {
            cardSuitSet.add(card.getSuit());
        }

        return cardSuitSet.toArray(new CardSuit[cardSuitSet.size()]);
    }

    public int getCardRankCount(CardRank cardRank) {
        int counter = 0;
        for(Card card : this) {
            if(card.getRank() == cardRank) {
                counter++;
            }
        }

        return counter;
    }

    public int getHighestCount() {
        CardRank[] ranks = getRanks();
        int highest = 0;
        for(CardRank rank : ranks) {
            int count = getCardRankCount(rank);
            if(count > highest) {
                highest = count;
            }
        }

        return highest;
    }
}
