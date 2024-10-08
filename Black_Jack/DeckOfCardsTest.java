public class DeckOfCardsTest {
    public static void main(String[] args) {
        DeckOfCards intr = new DeckOfCards();
        intr.intro();
        DeckOfCards myDeckOfCards = new DeckOfCards();
        
        myDeckOfCards.shuffle();

        myDeckOfCards.singleGame();

        //print all 52 Cards in the shaffled order
        /*for (int i = 1; i <= 52; i++) {
            System.out.printf("%-19s", myDeckOfCards.dealCard());
            
            if (i % 4 == 0) {
                System.out.println();
            }
        }*/
    }
}

