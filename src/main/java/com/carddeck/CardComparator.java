package com.carddeck;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        // Color priority: Red before Black
        int color1 = (c1.getSuit() == Suit.HEART || c1.getSuit() == Suit.DIAMOND) ? 0 : 1;
        int color2 = (c2.getSuit() == Suit.HEART || c2.getSuit() == Suit.DIAMOND) ? 0 : 1;
        if (color1 != color2) return Integer.compare(color1, color2);

        // Suit priority within color
        int suitOrder1 = getSuitOrder(c1.getSuit());
        int suitOrder2 = getSuitOrder(c2.getSuit());
        if (suitOrder1 != suitOrder2) return Integer.compare(suitOrder1, suitOrder2);

        // Rank comparison
        return Integer.compare(c1.getRank().ordinal(), c2.getRank().ordinal());
    }

    private int getSuitOrder(Suit suit) {
        return switch (suit) {
            case HEART -> 0;
            case DIAMOND -> 1;
            case SPADE -> 0;
            case CLUB -> 1;
        };
    }
}