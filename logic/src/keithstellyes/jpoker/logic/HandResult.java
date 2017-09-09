package keithstellyes.jpoker.logic;

import java.util.Arrays;

public enum HandResult {
    ROYAL_FLUSH {
        @Override
        public boolean inHand(Hand hand) {
            CardRank[] ranks = hand.getRanks();
            Arrays.sort(ranks);
            CardSuit[] suits = hand.getSuits();

            return suits.length == 1 &&
                    ranks.length == 5 &&
                    ranks[0] == CardRank.ACE &&
                    ranks[1] == CardRank.TEN &&
                    ranks[2] == CardRank.JACK &&
                    ranks[3] == CardRank.QUEEN &&
                    ranks[4] == CardRank.KING;
        }
    },
    STRAIGHT_FLUSH {
        @Override
        public boolean inHand(Hand hand) {
            CardRank[] ranks = hand.getRanks();
            Arrays.sort(ranks);
            CardSuit[] suits = hand.getSuits();
            return suits.length == 1 &&
                    ranks.length == 5 &&
                    ranks[4].ordinal() - ranks[0].ordinal() == 4;
        }
    },
    FOUR_OF_A_KIND {
        @Override
        public boolean inHand(Hand hand) {
            return hand.getHighestCount() >= 4;
        }
    },
    FULL_HOUSE {
        @Override
        public boolean inHand(Hand hand) {
            CardRank[] ranks = hand.getRanks();
            // [A] [A] [X] [B] [B] => X must be A or B, therefore it is full house
            return ranks.length == 5 &&
                    ranks[0] == ranks[1] &&
                    ranks[3] == ranks[4];
        }
    },
    FLUSH {
        @Override
        public boolean inHand(Hand hand) {
            return hand.getSuits().length == 1;
        }
    },
    STRAIGHT {
        @Override
        public boolean inHand(Hand hand) {
            CardRank[] ranks = hand.getRanks();
            Arrays.sort(ranks);

            return ranks.length == 5 &&
                    ranks[4].ordinal() - ranks[0].ordinal() == 4;
        }
    },
    THREE_OF_A_KIND {
        @Override
        // max unique ranks is 3: AAABC
        public boolean inHand(Hand hand) {
            return hand.getHighestCount() >= 3;
        }
    },
    TWO_PAIR {
        @Override
        // max unique ranks is 3: AABBC
        // X A A B B
        // A A X B B
        // A A B B X

        // A B C A A
        public boolean inHand(Hand hand) {
            CardRank[] ranks = hand.getRanks();
            int pairCount = 0;
            for(CardRank rank : ranks) {
                if(hand.getCardRankCount(rank) >= 2) {
                    pairCount++;
                }
            }

            return pairCount == 2;
        }
    },
    PAIR {
        //max unique ranks is 4: AABCD
        @Override
        public boolean inHand(Hand hand) {
            return hand.getRanks().length <= 4;
        }
    },
    HIGH_CARD {
        @Override
        public boolean inHand(Hand hand) {
            return true;
        }
    };

    public boolean inHand(Hand hand)
    {
        return false;
    }

    public static HandResult calculateHandResult(Hand hand) {
        for(HandResult result : HandResult.values()) {
            if(result.inHand(hand)) {
                return result;
            }
        }

        return null;
    }

    static {
        //our logic assumes this
        assert Hand.HAND_SIZE == 5;

        if(Hand.HAND_SIZE != 5) {
            throw new AssertionError("Expected hand size to be 5");
        }
    }
}
