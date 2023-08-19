package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Blackjack game that extends the Game class.
 * Manages the gameplay logic, including dealing cards, player turns,
 * and declaring the winner of the Blackjack game.
 * 
 * @author m_pdl
 */
public class BlackjackGame extends Game {

    private GroupOfCards deck;
    private Dealer dealer;

    public BlackjackGame(String name) {
        super(name);
        deck = new GroupOfCards(52);
        initializeDeck();
        dealer = new Dealer("Dealer");
    }

    //Initializes the deck by adding all 52 cards and shuffling them.
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                BlackjackCard card = new BlackjackCard(suit, rank);
                deck.addCard(card);
            }
        }

        deck.shuffle();
    }

    @Override
    public void play() {
        //Deal initial hand to player and dealer
        dealInitialHands();

        //Handle player turns
        for (Player player : getPlayers()) {
            handlePlayerTurn((BlackjackPlayer) player);
        }
        
        // Dealer's turn
        dealer.playTurn(deck);

        // Declare the winner of the game
        declareWinner();
    }

    //Deals initial hands to players and the dealer.
    private void dealInitialHands() {
        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            blackjackPlayer.addCardToHand((BlackjackCard) deck.getCards().remove(0));
            blackjackPlayer.addCardToHand((BlackjackCard) deck.getCards().remove(0));
        }

        dealer.addCardToHand((BlackjackCard) deck.getCards().remove(0));
        dealer.addCardToHand((BlackjackCard) deck.getCards().remove(0));

    }

    
    /**
     * Handles a player's turn during the Blackjack game.
     * Allows the player to hit (draw a card) or stand (end their turn).
     * 
     */
    public void handlePlayerTurn(BlackjackPlayer player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(player.getName() + "'s turn:");
        while (true) {
            // Display player's hand and ask for decision
            System.out.println("Your hand: " + player.getHand());
            System.out.println("Total value: " + calculateHandValue(player.getHand()));
            System.out.println("Do you want to hit or stand? (h/s)");
            String decision = scanner.nextLine().toLowerCase();

            if (decision.equals("h")) {
                // Player chooses to hit
                BlackjackCard drawnCard = (BlackjackCard) deck.getCards().remove(0);
                player.addCardToHand(drawnCard);
                int handValue = player.calculateHandValue();
                System.out.println("You drew: " + drawnCard);
                System.out.println("New hand value: " + handValue);
                if (handValue > 21) {
                    System.out.println("Bust! Your hand value is over 21.");
                    break;
                }
            } else if (decision.equals("s")) {
                // Player chooses to stand
                break;
            } else {
                // Invalid input, ask again
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    //Calculates hand value
    public int calculateHandValue(ArrayList<BlackjackCard> hand) {
        int value = 0;
        int numAces = 0;

        for (BlackjackCard card : hand) {
            value += getCardValue(card.getRank()); 
            if (card.getRank() == Rank.ACE) {
                numAces++;
            }
        }

        while (numAces > 0 && value > 21) {
            value -= 10; 
            numAces--;
        }

        return value;
    }
    
    //Assign hand values using enums
    private int getCardValue(Rank rank) {
        if (rank == Rank.ACE) {
            return 11; 
        } else if (rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING) {
            return 10;
        } else if (rank == Rank.TWO) {
            return 2;
        } else if (rank == Rank.THREE) {
            return 3;
        } else if (rank == Rank.FOUR) {
            return 4;
        } else if (rank == Rank.FIVE) {
            return 5;
        } else if (rank == Rank.SIX) {
            return 6;
        } else if (rank == Rank.SEVEN) {
            return 7;
        } else if (rank == Rank.EIGHT) {
            return 8;
        } else if (rank == Rank.NINE) {
            return 9;
        } else if (rank == Rank.TEN) {
            return 10;
        } else {
            throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    @Override
    public void declareWinner() {
        // Determine and display the winner of the Blackjack game
        int dealerValue = dealer.calculateHandValue();

        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            int playerValue = blackjackPlayer.calculateHandValue();

            System.out.println(player.getName() + "'s hand: " + blackjackPlayer.getHand());
            System.out.println(player.getName() + "'s hand value: " + playerValue);

            if (playerValue > 21) {
                System.out.println(player.getName() + " busts!");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println(player.getName() + " wins!");
            } else if (playerValue == dealerValue) {
                System.out.println(player.getName() + " ties with the dealer.");
            } else {
                System.out.println(player.getName() + " loses to the dealer.");
            }
        }

        // Display the dealer's hand
        System.out.println("Dealer's hand: " + dealer.getHand());
        System.out.println("Dealer's hand value: " + dealerValue);
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame("Welcome to Blackjack");
        System.out.println("Welcome to our Blackjack Game!");
        System.out.println("Please enter your name: ");
        Scanner input = new Scanner(System.in);
        String playerName = input.nextLine();
        game.getPlayers().add(new BlackjackPlayer(playerName));
        game.play();
    }

}
