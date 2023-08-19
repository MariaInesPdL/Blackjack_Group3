/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author m_pdl
 */
public class BlackjackCard extends Card {
    private Rank rank;
    private Suit suit;

    public BlackjackCard(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

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
