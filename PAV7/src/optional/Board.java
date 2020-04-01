package optional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clasa ce tine initial toti tokenii dintr-un joc in interiorul unei liste synchronized
 * creaaza tokenii dupa parametrii indicati in constructor
 *
 * Fata de compulsory am modificat anumite variabile ca fiind volatile sa se citeasca din ram direct
 * Acest lucru ajuta threadul principal cel ce anunta scorurile la final sa vada valori reale si nu cached
 *
 * Tine cont de timer prin intermediul threadului principal
 */
public class Board {
    private volatile boolean finished = false;
    private List<Token> tokens;
    private volatile int turn = 1;

    public Board(int n) {
        tokens = Collections.synchronizedList(new ArrayList<>());
        for (int index = 0; index < n; index++) {
            tokens.add(new Token(n));
        }
    }

    /**
     * Metoda folosita pentru a cauta un token dupa numarul lui
     * @param number -1 = Wildcard and 0< number <size este token cu numar obisnuit
     * @return returneaza pozitia tokenului respectiv
     * Folosita in principal de SmartPlayer
     */
    public int getTokenPosByNumber(int number) {
        int index = -1;
        Token token;
        if (number == -1) {
            token = new Token(number, true);
        }
        else
            token = new Token(number, false);
        try {
            index = tokens.indexOf(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }

    public int getSize() {
        return tokens.size();
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getTurn() {
        return turn;
    }

    /**
     * construieste un string ce contine sub o forma toti tokenii existenti inca in board
     *
     * @return stringul rezultat
     */
    public String getPrintTokens() {
        StringBuilder toBePrinted = new StringBuilder("Tokens: ");
        for (Token token : tokens) {
            if (token.isWildCard()) {
                toBePrinted.append("@ ");
            } else {
                toBePrinted.append(token.getNumber()).append(" ");
            }
        }
        return toBePrinted.toString();
    }

    public synchronized boolean isEmpty() {
        return tokens.isEmpty();
    }

    /**
     * Am modificat comportamentul complet, acum aceasta metoda adauga playerului direct tokenul
     * prin intermediul metodelor din player.
     * Aici se face si sincronizarea/semaforul/mutexul etc
     *
     * Fiecare isi asteapta tura indiferent de tipul de player
     * Daca un player castiga la o mutare accesul nu mai este permis pe urma la cei ce erau blocati in asteptare
     *
     * Daca un player face o miscare castigatoare boardul instiinteaza in consola
     * @param index pozitia care se vrea adaugata din board
     * @param player playerul ce face mutarea, necesar pentru operatiile facute si sincronizare
     *
     * Se face sincronizare -> se verifica incheiere -> se preia tokenul si se verifica conditiile jocului -> se face sincronizare
     */
    public synchronized void getToken(int index, Player player) {
        while (turn != player.getID()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!finished) {
            if (!isEmpty()) {
                Token token;
                token = tokens.get(index);
                System.out.print(getPrintTokens() + " -> ");
                tokens.remove(index);
                System.out.println(getPrintTokens() + "\n" + "Player " + player.getName() + " added " + token + "from index " + index);
                player.addSpecificToken(token);
                System.out.println(player.printTokens());
                if (player.hasArithmeticProgression()) {
                    player.setWon();
                    finished = true;
                }
            } else
                finished = true;
            System.out.println("");
        }
        turn = (turn + 1) % (Player.instanceCounter + 1);
        if (turn == 0)
            turn++;

        notifyAll();
    }
}
