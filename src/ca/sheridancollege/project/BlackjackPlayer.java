package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Represents a player in a Blackjack game.
 * This class extends the Player class and provides methods
 * for managing a player's hand and calculating hand value.
 * 
 * @author m_pdl
 */

public class BlackjackPlayer extends Player {

    /**
     * Constructs a BlackjackPlayer object with the given name.
     * Initializes the player's hand as an empty ArrayList.
     */
    
    private ArrayList<BlackjackCard> hand;

    public BlackjackPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    //Retrieves the player's hand of cards.
    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    //Adds a card to the player's hand.
    public void addCardToHand(BlackjackCard card) {
        hand.add(card);
    }

    //Calculates the value of the player's hand.
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


    @Override
    public void play() {}
}
