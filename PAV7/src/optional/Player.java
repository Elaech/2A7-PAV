package optional;

import java.util.ArrayList;
import java.util.List;
/**
 * Clasa NU MAI dicteaza tot ce se intampla in interiorul jocului
 * Mai exact isi ia elemente din board-ul dat prin referinta in constructor pana cand formeaza o progresie sau bordul e gol
 * Runnable este pentru a permite lucrul cu threads
 *
 * Fata de compulsory am mutat tot lucrul cu bordul in Board deoarece mi se parea mai etic
 * Playerul trebuie sa stie doar ce mutare face si ce anume are la tokenii alesi nu si cum se intampla acest lucru
 *
 * Am adaugat si niste membri ca fiind volatile pentru threadul principal
 */
public abstract class Player implements Runnable{
    public static int instanceCounter = 0;
    protected final int ID;
    protected final String name;
    protected Board board;
    protected List<Token> tokensAcquired;
    protected int sizeOfProgression;
    protected volatile boolean won = false;
    protected volatile boolean terminated = false;
    public Player(String name, Board b, int sizeOfProgression) {
        this.name = name;
        board = b;
        ID = ++instanceCounter;
        tokensAcquired = new ArrayList<>();
        this.sizeOfProgression = sizeOfProgression;
    }
    public String getName() {
        return name;
    }

    public boolean won() {
        return won;
    }

    public void setWon() {
        won = true;
    }


    public boolean isTerminated() {
        return terminated;
    }

    public int getID() {
        return ID;
    }
    /**
     * Afiseaza progresia din multimea de tokeni a playerului
     *
     * @param index - de unde sa afiseze
     */
    protected void printProgression(int index) {
        StringBuilder stringBuilder = new StringBuilder("Winning " + name + ": ");
        for (int i = index; i < (index + sizeOfProgression); i++) {
            stringBuilder.append(tokensAcquired.get(i));
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * verifica daca exista o progresie la parametrii indicati de lungimea din constructor specificata
     *
     * @param index de la ce element sa verifice pentru a gasi progresia
     * @return este progresie sau nu
     */
    protected boolean isProgression(int index) {
        int lastIncrement = 0;
        boolean isFirstIncrement = true;
        for (int i = index; i < (index + sizeOfProgression - 1); i++) {
            if (!tokensAcquired.get(i).isWildCard
                    && !tokensAcquired.get(i + 1).isWildCard) {
                if (isFirstIncrement) {
                    lastIncrement = tokensAcquired.get(i + 1).getNumber() - tokensAcquired.get(i).getNumber();
                    isFirstIncrement = false;
                } else if ((tokensAcquired.get(i + 1).getNumber() - tokensAcquired.get(i).getNumber()) != lastIncrement)
                    return false;
            }
        }
        return true;
    }
    /**
     * Verifica daca este vreo progresia in toata multimea de tokeni a playerului
     *
     * @return exista o progresie sau nu in multimea de tokeni
     */
    public boolean hasArithmeticProgression() {
        if (tokensAcquired.size() < sizeOfProgression)
            return false;
        for (Token token : tokensAcquired) {
            int firstElementIndex = tokensAcquired.indexOf(token);
            if (firstElementIndex <= tokensAcquired.size() - sizeOfProgression) {
                if (isProgression(firstElementIndex)) {
                    printProgression(firstElementIndex);
                    return true;
                }
            }
        }
        return false;
    }
    protected void addToken(int index) {
        board.getToken(index, this);
    }
    public void addSpecificToken(Token token) {
        tokensAcquired.add(token);
        sortTokens();
    }

    public void sortTokens() {
        tokensAcquired.sort((o1, o2) -> {
            if (o1.isWildCard) {
                return -1;
            } else if (o2.isWildCard) {
                return 1;
            } else {
                return o1.getNumber() - o2.getNumber();
            }
        });
    }
    /**
     * creaza un string de afisare cu toti tokenii playerului folosind un builder
     *
     * @return returneaza stringul cu tokenii
     */
    public String printTokens() {
        StringBuilder toBePrinted = new StringBuilder(getName() + ": ");
        for (Token token : tokensAcquired) {
            if (token.isWildCard)
                toBePrinted.append("@ ");
            else
                toBePrinted.append(token.getNumber()).append(" ");
        }
        return toBePrinted.toString();
    }
    /**
     * metoda initiala ce va rula pe thread
     * ea trebuie suprascrisa pentru comportament diferit
     * initial adauga mereu primul element din board
     * adauga tokeni pana cand jocul este gata si specifica la final ca threadul pe care rula si-a terminat activitatea
     */
    @Override
    public void run() {
        while (!board.isFinished()) {
            addToken(0);
        }
        terminated = true;
    }
}
