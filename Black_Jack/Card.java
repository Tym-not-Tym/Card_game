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
        if (face.startsWith("A") || face.startsWith("J") 
                || face.startsWith("Q") || face.startsWith("K") ) {
            if (face.startsWith("A")) {
                return 11;
            } else {
                return 10;
            }
         } else {
            return Integer.parseInt(face);
         }
    }
}