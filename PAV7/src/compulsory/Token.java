package compulsory;

/**
 * Clasa ce se ocupa cu obiectul atomic al acestui joc
 * Contine un numar sau poate juca rol de wildcard pentru orice numar
 * Aceste valori specifica sunt initializate random cu o sansa mica pentru wildcard.
 */
public class Token{
    private Integer number;
    boolean isWildCard = false;

    public Token(int m) {
        if (Math.random() > 0.90)
            isWildCard = true;
        else {
            number = (int) (Math.random() * m) + 1;
        }
    }

    public int getNumber() {
        return number;
    }

    public boolean isWildCard() {
        return isWildCard;
    }

    /**
     * Implementata pentru testare si afisare
     * @return string variant of token
     */
    @Override
    public String toString() {
        if(isWildCard)
            return "@ ";
        return number.toString()+" ";
    }
}

