package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author m_pdl
 */

public class BlackjackPlayer extends Player {

    private ArrayList<BlackjackCard> hand;

    public BlackjackPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    public void addCardToHand(BlackjackCard card) {
        hand.add(card);
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

    @Override
    public void play() {
        // Blackjack player logic (hit, stand, split) will go here
    }
}
