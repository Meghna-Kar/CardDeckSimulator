package com.carddeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setup() {
        deck = new Deck();
    }

    @Test
    public void testDeckInitialization() {
        assertEquals(52, deck.remainingCards(), "Deck should contain 52 cards on init.");
    }

    @Test
    public void testCardDrawing() {
        List<Card> drawn = deck.drawCards(20);
        assertEquals(20, drawn.size(), "Should draw 20 cards.");
        assertEquals(32, deck.remainingCards(), "Deck should have 32 cards left.");
    }

    @Test
    public void testShufflingChangesOrder() {
        List<Card> original = new ArrayList<>(deck.getDeck());
        deck.shuffle();
        assertNotEquals(original, deck.getDeck(), "Deck should be in a different order after shuffling.");
    }

    @Test
    public void testSortingCards() {
        List<Card> drawn = deck.drawCards(20);
        Collections.sort(drawn, new CardComparator());

        for (int i = 0; i < drawn.size() - 1; i++) {
            assertTrue(new CardComparator().compare(drawn.get(i), drawn.get(i + 1)) <= 0,
                    "Cards should be sorted based on color, suit, and rank.");
        }
    }
}