/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Represents the Dealer in a Blackjack game.
 * This class extends the BlackjackPlayer class and provides methods
 * for managing the dealer's hand and playing their turn.
 * @author m_pdl
 */
class Dealer extends BlackjackPlayer {
    
    /**
     * Constructs a Dealer object with the given name.
     * Initializes the dealer's hand as an empty ArrayList.
     */
    private ArrayList<BlackjackCard> hand;

    public Dealer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    //Retrieves the dealer's hand of cards.
    @Override
    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    //Adds a card to the dealer's hand.
    @Override
    public void addCardToHand(BlackjackCard card) {
        hand.add(card);
    }

    //The dealer draws cards until their hand value reaches 17 or more.
    public void playTurn(GroupOfCards deck) {
        while (calculateHandValue() < 17 && !deck.getCards().isEmpty()) {
            addCardToHand((BlackjackCard) deck.getCards().remove(0));
        }
    }

     /**
     * Calculates the hand value.
     * Aces are counted as 11 until the hand value would exceed 21.
     * Then, Aces are counted as 1 to avoid busting.
     * 
     */
    public int calculateHandValue() {
        int value = 0;
        int numAces = 0;

        for (BlackjackCard card : getHand()) {
            value += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }

        while (numAces > 0 && value > 21) {
            value -= 10; // Adjust for Aces
            numAces--;
        }

        return value;
    }

}
