/* Dylan Torres, Tymofiy Grebenyuk, Agastya M
10/8/2024
CS& 145
Lab 2: Card Game 

This program lets the user play Blackjack.

For extra credit, we implemented: Exception handling, switch/case statements, try/catch blocks, and printf, and little bit of GUI.*/

import java.util.Scanner;

public class DeckOfCardsTest {
    public static void main(String[] args) {
        BlackJackGUI GUI = new BlackJackGUI(); 
        Scanner scanner = new Scanner(System.in);
        DeckOfCards intr = new DeckOfCards();
        intr.intro();
        DeckOfCards myDeckOfCards = new DeckOfCards();
        String input;
        do {//while loop for playing multiple games
            myDeckOfCards.shuffle();//shuffle cards
            myDeckOfCards.singleGame();//plays a single game
            while (true) {
                System.out.println("\nDo you want to play again? ");
                input = scanner.next().toUpperCase();
                if (input.startsWith("Y") || input.startsWith("N")) {
                    break; //if input is valid, exit
                } else {
                    System.out.println("Invalid input. Please enter Y or N");
                }
            }
        } while (input.startsWith("Y"));
        
    }




}
