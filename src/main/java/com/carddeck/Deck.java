package com.carddeck;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> drawCards(int count) {
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < count && !cards.isEmpty(); i++) {
            drawn.add(cards.remove(0));
        }
        return drawn;
    }

    public int remainingCards() {
        return cards.size();
    }

    public List<Card> getDeck() {
        return cards;
    }
}