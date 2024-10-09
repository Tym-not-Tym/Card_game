import java.util.Scanner;

public class DeckOfCardsTest {
    public static void main(String[] args) {
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
