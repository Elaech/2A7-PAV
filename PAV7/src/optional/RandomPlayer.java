package optional;


/**
 * subclasa a player si face alegeri random
 */
public class RandomPlayer extends Player {

    public RandomPlayer(String name, Board b, int sizeOfProgression) {
        super(name, b, sizeOfProgression);
    }

    /**
     * alege o pozitie random pe care o paseaza functiei addToken din Player
     */
    @Override
    public void run() {
        while (!board.isFinished()) {
            addToken((int) (Math.random()*(board.getSize()-1)));
        }
        terminated = true;
    }
}
