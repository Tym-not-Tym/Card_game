import java.security.SecureRandom;
import java.util.Scanner;

import javax.smartcardio.Card;

public class DeckOfCards {
    //random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;
    
    private Card[] deck = new Card[NUMBER_OF_CARDS];
    private int currentCard = 0; // index of next Card to be dealt

    // constructor fills deck of Cards
    public DeckOfCards() {
        
        String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Heart", "Diamonds", "Clubs", "Spades"};

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() {
        // next call to method dealCard should start ot deck[0] again
        currentCard = 0;

        // for each Card, pick another random Card and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            //swap current Card with randomlyselected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    public Card dealCard() {
        if (currentCard < deck.length) {
            //System.out.print(deck[currentCard].getValue() + " ");//to test getValue
            return deck[currentCard++]; // return current Card in array
        } else {
            return null; // return null to indicate that all Cards were dealt
        }
    }

    public void intro() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("The goal of the game is to beat the dealer's hand without going over 21.");
        System.out.println("Face cards (Jack, Queen, and King) are worth 10 points.");
        System.out.println("Aces can be worth either 1 or 11 points, whichever benefits your hand.");
        System.out.println("You and the dealer will each be dealt two cards.");
        System.out.println("You'll have the option to either 'Hit' to draw another card or 'Stand' to keep your current hand.");
        System.out.println("If your hand exceeds 21, you 'Bust' and automatically lose.");
        System.out.println("If you don't bust, the dealer will then reveal their cards and try to beat your hand.");
        System.out.println("Good luck!\n");
    }

    public void singleGame() {
        Scanner scn = new Scanner(System.in);
        Card[] dealCards = new Card[20];//array to store "dealed" cards
        Card[] playeCards = new Card[20];
        String consol;

        dealCards[1] = dealCard();//for dealer
        dealCards[2] = dealCard();

        playeCards[1] = dealCard();//for player
        playeCards[2] = dealCard();

        System.out.println("DEALER CARDS:");
        System.out.println("* / " + dealCards[2].toString() + "\n");

        System.out.println("YOUR CARDS:");
        System.out.println(playeCards[1].toString() + " / " + playeCards[2].toString() + "\n");

        int cardsValue = 0;
        for (int i = 1; i <= 2; i++){
            cardsValue += playeCards[i].getValue();
        }

        if (cardsValue == 21) {//Check if the player wins at the very beginning.
            System.out.println("You Win!");
        } else {
            boolean flag = false;//for while loop to hit or stay
            int hitCount = 2;//used to add additional cards for player
            do {
                System.out.println("Hit or Stay");
                consol = scn.next().toUpperCase();
                if (consol.startsWith("H")) {
                    playeCards[++hitCount] = dealCard();
                    System.out.println(playeCards[hitCount].toString() + "\n");
                    flag = true;
                } else {
                    flag = false;
                }
            } while (flag);
        }

    }
}

