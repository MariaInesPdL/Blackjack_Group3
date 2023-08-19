/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * Represents a card used in a Blackjack game.
 * This class extends the Card class and provides methods to get the card's rank, suit,
 * display the card as a string, and calculate its value in the context of the game.
 * 
 * @author m_pdl
 */
public class BlackjackCard extends Card {
    private Rank rank;
    private Suit suit;

    //Constructs a BlackjackCard with the specified suit and rank.
    public BlackjackCard(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    //Retrieves the rank of the card.
    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    //Calculates the value of the card
    public int getValue() {
        if (rank == Rank.ACE) {
            return 11; // Can be adjusted during hand calculations
        } else if (rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING) {
            return 10;
        } else {
            return rank.ordinal() + 2; // For ranks TWO to TEN
        }
    }
}
