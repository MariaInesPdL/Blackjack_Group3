/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author m_pdl
 */
class Dealer extends BlackjackPlayer {

    private ArrayList<BlackjackCard> hand;

    public Dealer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    @Override
    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    @Override
    public void addCardToHand(BlackjackCard card) {
        hand.add(card);
    }

    public void playTurn(GroupOfCards deck) {
        while (calculateHandValue() < 17 && !deck.getCards().isEmpty()) {
            addCardToHand((BlackjackCard) deck.getCards().remove(0));
        }
    }

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
