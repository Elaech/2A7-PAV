package compulsory;

/**
 * Creeaza cei 2 playeri si bordul si pune playerii sa se joace pe threaduri diferite
 *
 */
public class Game {
    Player player1;
    Player player2;
    Board board;
    String finalString = "Game result: ";
    public Game(String name1, String name2, int boardDimension,int numberOfProgression) {
        board = new Board(boardDimension);
        player1 = new Player(name1, board,numberOfProgression);
        player2 = new Player(name2, board,numberOfProgression);
    }

    /**
     * Cazurile tratate sunt pentru castig: castiga P1 , castiga P2, iar pentru draw: amandoi castiga, amandoi pierd
     * @return Verifica daca jocul este gata sau nu
     */
    public boolean isGameFinished(){
        if(player1.won() && player2.won()){
            finalString += "Draw!";
            return true;
        }
        else if (player2.won()) {
            player1.setGameFinished();
            finalString += player2.getName() + " has won!";
            return true;
        } else if (player1.won()) {
            player2.setGameFinished();
            finalString += player1.getName() + " has won!";
            return true;
        }
        else if(player1.isGameFinished()&&player2.isGameFinished()){
            finalString += "Draw!";
            return true;
        }
        return false;
    }

    /**
     * pune playerii pe threaduri diferite si asteapta terminarea jocului
     * cand se termina threadurile va afisa detaliile fiecarui player
     * si un mesaj de final (Ex: P1 has won!)
     */
    public void start(){
        board.getPrintTokens();
        new Thread(player1).start();
        new Thread(player2).start();
        while(true){
            synchronized (this) {
                if (isGameFinished())
                    break;
            }
        }
        while(!player2.isTerminated() || !player1.isTerminated());
        System.out.println("Game Finished!");
        System.out.println(player1.printTokens()+"\n" +player2.printTokens());
        System.out.println(finalString);
    }
}
