package optional;

import java.util.Scanner;

/**
 * Clasa specializata in lucru cu utilizatorul
 * Va citi din consola input pentru pozitia dorita (a se nota ca pozitia aici incepe de la 1 - QoL pt user)
 */
public class ManualPlayer extends Player{
    public ManualPlayer(String name, Board b, int sizeOfProgression) {
        super(name, b, sizeOfProgression);
    }

    /**
     * In timpul turei utilizatorului va incerca sa ia o pozitie valida de la acesta
     * Tine cont si de timer prin intermediul bordului
     *
     * @return pozitia aleasa de User
     */
    private int getUserInputForIndex(){
        Scanner scanner = new Scanner(System.in);
        boolean badUser = true;
        int index = 0;
        while(board.getTurn()!=ID);
        if(!board.isFinished()) {
            System.out.println("Pick a token!");
            while (badUser && !board.isFinished()) {
                try {
                    index = Integer.parseInt(scanner.next());
                    index--;
                    if (index >= 0 && index < board.getSize())
                        badUser = false;
                } catch (Exception e) {
                }
            }
        }
        return index;
    }

    /**
     * Ia user input si il transmite catre addToken din player
     */
    @Override
    public void run() {
        while (!board.isFinished()) {
            addToken((int) (getUserInputForIndex()));
        }
        terminated = true;
    }
}
