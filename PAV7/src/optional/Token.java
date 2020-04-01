package optional;

/**
 * Clasa ce se ocupa cu obiectul atomic al acestui joc
 * Contine un numar sau poate juca rol de wildcard pentru orice numar
 * Aceste valori specifica sunt initializate random cu o sansa mica pentru wildcard.
 */
public class Token {
    private Integer number;
    boolean isWildCard = false;

    public Token(int m) {
        if (Math.random() > 0.90){
            isWildCard = true;
            number = 0;
        }
        else {
            number = (int) (Math.random() * m) + 1;
        }
    }

    /**
     * Am adaugat un constructor ce va fi folosit de functia de cautare de tokeni pentru a crea tokeni specifici din board
     * @param number numarul tokenului
     * @param setWildcard daca este wildcard
     */
    public Token(int number, boolean setWildcard) {
        if (setWildcard) {
            isWildCard = true;

        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isWildCard() {
        return isWildCard;
    }

    /**
     * Implementata pentru testare si afisare
     *
     * @return string variant of token
     */
    @Override
    public String toString() {
        if (isWildCard)
            return "@ ";
        return number.toString() + " ";
    }

    /**
     * Am dat override la equals pentru a cauta prin array cu functia mea
     * wildcard = wildcard
     * else
     * number = number
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            Token token = (Token) obj;
            if (isWildCard() && (token.isWildCard()))
                return true;
            if (number == token.getNumber())
                return true;
            return false;

        }
        return false;
    }
}

