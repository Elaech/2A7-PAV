package compulsory;

import java.util.*;

/**
 * Clasa ce dicteaza tot ce se intampla in interiorul jocului
 * Mai exact isi ia elemente din board-ul dat prin referinta in constructor pana cand formeaza o progresie sau bordul e gol
 * Runnable este pentru a permite lucrul cu threads
 */
public class Player implements Runnable {
    private final String name;
    private Board board;
    private List<Token> tokensAcquired;
    private int sizeOfProgression;
    private boolean gameFinished = false;
    private boolean won =false;
    private boolean terminated = false;
    public Player(String name, Board b, int sizeOfProgression) {
        this.name = name;
        board = b;
        tokensAcquired = new ArrayList<>();
        this.sizeOfProgression = sizeOfProgression;
    }

    public synchronized void setGameFinished() {
        this.gameFinished = true;
    }

    public synchronized boolean isGameFinished() {
        return gameFinished;
    }

    public String getName() {
        return name;
    }

    public synchronized boolean won() {
        return won;
    }

    public boolean isTerminated() {
        return terminated;
    }

    /**
     * Afiseaza progresia din multimea de tokeni a playerului
     * @param index - de unde sa afiseze
     */
    private void printProgression(int index){
        StringBuilder stringBuilder = new StringBuilder("Winning "+name+": ");
        for(int i=index;i<(index+sizeOfProgression);i++) {
            stringBuilder.append(tokensAcquired.get(i));
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * verifica daca exista o progresie la parametrii indicati de lungimea din constructor specificata
     * @param index de la ce element sa verifice pentru a gasi progresia
     * @return este progresie sau nu
     */
    private boolean isProgression(int index){
        int lastIncrement = 0;
        boolean isFirstIncrement= true;
        for(int i=index;i<(index+sizeOfProgression-1);i++){
            if(!tokensAcquired.get(i).isWildCard
                    && !tokensAcquired.get(i+1).isWildCard){
                if(isFirstIncrement) {
                    lastIncrement = tokensAcquired.get(i + 1).getNumber() - tokensAcquired.get(i).getNumber();
                    isFirstIncrement = false;
                }
                else
                    if((tokensAcquired.get(i+1).getNumber()- tokensAcquired.get(i).getNumber())!=lastIncrement)
                        return false;
            }
        }
        return true;
    }

    /**
     * Verifica daca este vreo progresia in toata multimea de tokeni a playerului
     * @return exista o progresie sau nu in multimea de tokeni
     */
    public boolean hasArithmeticProgression() {
        if(tokensAcquired.size()<sizeOfProgression)
            return false;
        for (Token token : tokensAcquired) {
            int firstElementIndex = tokensAcquired.indexOf(token);
            if(firstElementIndex<= tokensAcquired.size()-sizeOfProgression){
                if(isProgression(firstElementIndex)){
                    printProgression(firstElementIndex);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * verifica daca boardul este gol pentru a vedea daca se mai poate juca
     * chiar daca bordul nu este gol acesta poate fi in momentul adaugarii sau stergerii din cauza concurentei deci trebuie tratata eroarea ce va aparea atunci deci try/catch
     * luam tokenul din board si il adaugam la lista playerului
     * se sorteaza lista playerului cu lambda
     * si se verifica lista de progresii
     */
    private void addToken() {
        String toBePrinted = "";
        if(!board.isEmpty()) {
            try {
                    Token token = board.getToken(0);
                    tokensAcquired.add(token);
                    toBePrinted+= board.getPrintTokens();
                    toBePrinted+= "\n"+printTokens();
                    System.out.println(toBePrinted);

            } catch (Exception ignored) { }
            tokensAcquired.sort((o1, o2) -> {
                if (o1.isWildCard) {
                    return -1;
                } else if (o2.isWildCard) {
                    return 1;
                } else {
                    return o1.getNumber() - o2.getNumber();
                }
            });
            if(hasArithmeticProgression()) {
                setGameFinished();
                won =true;
            }
        }
        else
            setGameFinished();
    }

    /**
     * creaza un string de afisare cu toti tokenii playerului folosind un builder
     * @return returneaza stringul cu tokenii
     */
    public String printTokens(){
        StringBuilder toBePrinted = new StringBuilder(getName() + ": ");
        for(Token token: tokensAcquired){
            if(token.isWildCard)
                toBePrinted.append("@ ");
            else
                toBePrinted.append(token.getNumber()).append(" ");
        }
        return toBePrinted.toString();
    }

    /**
     * metoda initiala ce va rula pe thread
     * adauga tokeni pana cand jocul este gata si specifica la final ca threadul pe care rula si-a terminat activitatea
     */
    @Override
    public void run() {
        while (!gameFinished) {
            addToken();
        }
        terminated = true;
    }
}
