package com.carddeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeckGUI extends JFrame {

    private final Deck deck;
    private final DefaultListModel<String> cardListModel;
    private final JLabel deckSizeLabel;

    public CardDeckGUI() {
        super("Card Deck Simulator");
        deck = new Deck();
        cardListModel = new DefaultListModel<>();
        deckSizeLabel = new JLabel("Remaining Cards: " + deck.remainingCards());

        initComponents();
    }

    private void initComponents() {
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buttons
        JButton shuffleButton = new JButton("Shuffle Deck");
        JButton drawButton = new JButton("Draw 20 Cards");
        JButton sortButton = new JButton("Sort Cards");

        // Event Listeners
        shuffleButton.addActionListener(this::shuffleDeck);
        drawButton.addActionListener(this::drawCards);
        sortButton.addActionListener(this::sortCards);

        // List to show drawn cards
        JList<String> cardList = new JList<>(cardListModel);
        JScrollPane scrollPane = new JScrollPane(cardList);

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10));
        panel.add(shuffleButton);
        panel.add(drawButton);
        panel.add(sortButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(deckSizeLabel, BorderLayout.WEST);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    private void shuffleDeck(ActionEvent e) {
        deck.shuffle();
        JOptionPane.showMessageDialog(this, "Deck has been shuffled.");
    }

    private void drawCards(ActionEvent e) {
        cardListModel.clear();
        List<Card> drawnCards = deck.drawCards(20);
        for (Card card : drawnCards) {
            cardListModel.addElement(card.toString());
        }
        deckSizeLabel.setText("Remaining Cards: " + deck.remainingCards());
    }

    private void sortCards(ActionEvent e) {
        List<String> currentCards = Collections.list(cardListModel.elements());
        List<Card> cardObjects = currentCards.stream()
                .map(this::stringToCard)
                .collect(Collectors.toList());
        Collections.sort(cardObjects, new CardComparator());

        cardListModel.clear();
        for (Card card : cardObjects) {
            cardListModel.addElement(card.toString());
        }
    }

    // Utility to convert "RANK of SUIT" to Card object
    private Card stringToCard(String str) {
        String[] parts = str.split(" of ");
        Rank rank = Rank.valueOf(parts[0]);
        Suit suit = Suit.valueOf(parts[1]);
        return new Card(suit, rank);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardDeckGUI().setVisible(true));
    }
}