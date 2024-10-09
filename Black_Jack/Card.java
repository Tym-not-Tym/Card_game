public class Card {
    private final String face; 
    private final String suit;

    public Card(String cardFace, String cardSuit) {
        this.face = cardFace;
        this.suit = cardSuit;
    }

    public String toString() {
        return face + " of " + suit;
    }

    public int getValue() {
    
        //stores the face of the card as an integer value 
        switch (face) {
            case "Ace":
                return 11; 
            case "King":
            case "Queen":
            case "Jack":
                return 10; 
            default: 
                return Integer.parseInt(face); //all other cards hold designated value 

        }
    }        
                 
}