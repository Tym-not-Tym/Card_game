import java.security.SecureRandom;
import java.util.Scanner;

import javax.smartcardio.Card;

public class DeckOfCards {
    //random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;
    int totalChips = 200; ///chips for the player
    
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
        int numOfCardsD = 2;//used to add additional cards for dealer
        int numOfCardsP = 2;//used to add additional cards for player
        int cardsValueP = 0;//cards value for player
        int cardsValueD = 0;//cards value for dealer
         
        System.out.printf("You currently have " + totalChips + " Chips\n"); // displays your chip amount
        
        if (totalChips == 0){  //exits the game if you ar all out of chips 
            System.out.println("Seem's you are all out, come back another time!");
            System.exit(0);
        }
        int betChips = getBet(scn); //user inputted bet amount
    
        String consol;

        dealCards[1] = dealCard();//for dealer
        dealCards[2] = dealCard();

        playeCards[1] = dealCard();//for player
        playeCards[2] = dealCard();

        System.out.println("DEALER CARDS:");
        System.out.println("* / " + dealCards[2].toString() + "\n");

        System.out.println("YOUR CARDS:");
        printCardsOnHand(numOfCardsP, playeCards);

        cardsValueP = getCardsValue(numOfCardsP, playeCards);
        if (cardsValueP == 21) {//Check if the player wins at the very beginning.
            totalChips = chipCounter(totalChips, betChips, true);
            System.out.println("\nWOW!, lucky. You now have " + totalChips);   //////////////////////////////// po//p up prompt once action is completed
        } else {
            boolean flag = false;//for while loop to hit or stay
            do {
                System.out.print("\nHit or Stay ");    
                consol = scn.next().toUpperCase();
                if (consol.startsWith("H")) { //////////////////////////// can be ran into a button
                    playeCards[++numOfCardsP] = dealCard();
                    System.out.println(playeCards[numOfCardsP].toString() + "\n");
                    flag = true;
                    cardsValueP = getCardsValue(numOfCardsP, playeCards);
                    if (cardsValueP > 21) {//if player exided 21
                        System.out.println("You Lost!"); ///////////////////// on screen prompt 
                        totalChips = chipCounter(totalChips, betChips, false);
                        System.out.println(totalChips + " Chips left!");
                        flag = false;
                    } else if (cardsValueP == 21) {//Check if the player wins
                        System.out.println("You Win!");////////////////////// on screen prompt
                        totalChips = chipCounter(totalChips, betChips, true);
                        System.out.println("Nice! You now have " + totalChips);
                        flag = false;
                    }
                    //System.out.println(cardsValueP);//print value of my cards
                    
                    
                } else if (consol.startsWith("S")){//if entered Stay ////////////// can be ran into a button 
                    flag = false;
                    do {//add additional cards to dealer if <17
                        cardsValueD = getCardsValue(numOfCardsD, dealCards);
                        if (cardsValueD < 17) {
                            dealCards[++numOfCardsD] = dealCard();
                        }
                    } while (cardsValueD < 17);

                    System.out.println("DEALER CARDS:");
                    printCardsOnHand(numOfCardsD, dealCards); //prints dealer's cards 

                    if (cardsValueD > 21) {//if player exided 21
                        System.out.println("\nYou Win!");  //////////////////////// prompt
                        totalChips = chipCounter(totalChips, betChips, true);
                        System.out.println("STRAAAAAIIIIGHT, you now have " + totalChips + "!"); 
                    } else {                    
                        cardsValueP = getCardsValue(numOfCardsP, playeCards);//compare the values of the cards and choose a winner
                        if (cardsValueD > cardsValueP) {
                            System.out.println("\nYou Lost!");
                            totalChips = chipCounter(totalChips, betChips, false);
                            System.out.println("Total chips after losing the bet: " + totalChips);

                        } else if (cardsValueD < cardsValueP){
                            System.out.println("\nYou Win!");
                            totalChips = chipCounter(totalChips, betChips, true);
                            System.out.println("Nice! You now have " + totalChips);
                        } else {
                            System.out.println("\nIt's a Tie");
                            totalChips = chipCounter(totalChips, betChips, true);
                            System.out.println(totalChips + " Chips left!");
                        }
                    }
                }
            } while (flag);
        }

    }


    public int getBet(Scanner scn) {
        int betChips = 0; // create betting chips 
        while (true) {
            System.out.print("How much are we betting? ");
            try {
                betChips = Integer.parseInt(scn.nextLine()); //populate betting chips
                if (betChips > totalChips) {
                    System.out.println("You only have " + totalChips + " Chips");
                } else if (betChips <= 0) { //input validation
                    System.out.println("Please enter a positive number."); 
                } else {
                    return betChips;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number."); //Ensure input is integer and not string
            }
        }
    }


    public int chipCounter(int totalChips, int betChips, boolean result) {
        
        //if player wins give chips else decrement chips  
        if (result) {
            return totalChips + betChips;
        } else {
            //if player loses dealer pays out chips
            if(totalChips >= betChips){
                return totalChips - betChips;
            }  else {
                System.out.println("Not enough chips, better luck next time");
                return totalChips; 
            }

        }                   
         
    }

    public int getCardsValue(int numOfCards, Card[] playeCards) {//get value of the cards on hand
        int cardsValue = 0;
        for (int i = 1; i <= numOfCards; i++){
            cardsValue += playeCards[i].getValue();
        }
        return cardsValue;
    }

    public void printCardsOnHand(int numOfCards, Card[] playeCards) {
        for (int i = 1; i <= numOfCards ; i++) {
            System.out.print(playeCards[i].toString() + "  ");
        }
    }     

}

