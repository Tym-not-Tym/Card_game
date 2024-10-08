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
            System.out.println("\nDo you want to play again? ");
            input = scanner.next().toUpperCase();
        } while (input.startsWith("Y"));
    }
}














//print all 52 Cards in the shaffled order
        /*for (int i = 1; i <= 52; i++) {
            System.out.printf("%-19s", myDeckOfCards.dealCard());
            
            if (i % 4 == 0) {
                System.out.println();
            }
        }*/