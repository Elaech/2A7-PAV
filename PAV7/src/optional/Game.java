package optional;

/**
 * Creeaza cei 2 playeri si bordul si pune playerii sa se joace pe threaduri diferite
 * cei 2 playeri pot fi de 3 tipuri: 1-random 2-manual 3-smart
 * verificarea pentru terminare se face in 2 moduri: prin timer si prin board daca jocul este gata
 * in cazul in care una din conditiile mentionate este pozitiva atunci threadul principal pe care ruleaza instanta Game
 * are grija si semnaleaza tuturor threadurilor ce nu sunt constiente de timer sau board ca trebuie sa se opreasca din activitati
 *
 * la final dupa terminarea threadurilor player va afisa cine a castigat si situatia finala
 */
public class Game {
    Player Player1;
    Player Player2;
    Board board;
    TimeKeeper timeKeeper ;
    String finalString = "Game result: ";
    public Game(String name1,int type1, String name2,int type2, int boardDimension,int numberOfProgression, int time) {
        board = new Board(boardDimension);

        switch (type1){
            case 1: {
                Player1 = new RandomPlayer(name1, board,numberOfProgression);
                break;
            }
            case 2:{
                Player1 = new ManualPlayer(name1, board,numberOfProgression);
                break;
            }
            case 3:{
                Player1 = new SmartPlayer(name1, board,numberOfProgression);
                break;
            }
        }

        switch (type2){
            case 1: {
                Player2 = new RandomPlayer(name2, board,numberOfProgression);
                break;
            }
            case 2:{
                Player2 = new ManualPlayer(name2, board,numberOfProgression);
                break;
            }
            case 3:{
                Player2 = new SmartPlayer(name2, board,numberOfProgression);
                break;
            }
        }

        timeKeeper = new TimeKeeper(time);
    }

    /**
     * Cazurile tratate sunt pentru castig: castiga P1 , castiga P2, iar pentru draw: amandoi castiga, amandoi pierd
     * @return Verifica daca jocul este gata sau nu
     */
    public  boolean isGameFinished(){
        if(timeKeeper.isFinished()){
            finalString += "Draw! Time Expired!";
            board.setFinished(true);
            timeKeeper.setStop();
            return true;
        }
        else if(board.isFinished()){
            timeKeeper.setStop();
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
        System.out.println(board.getPrintTokens());
        new Thread(Player1).start();
        new Thread(Player2).start();
        new Thread(timeKeeper).start();


        while(true){
            if (isGameFinished())
                break;
        }
        while(true){
            if(Player1.isTerminated()&& Player2.isTerminated())
                break;
        }
        if (Player2.won()) {
            finalString += Player2.getName() + " has won!";
        } else if (Player1.won()) {
            finalString += Player1.getName() + " has won!";
        }
        else if(finalString.equals("Game result: ")){
            finalString+= "Draw!";
        }
        System.out.println("Game Finished!");
        System.out.println(Player1.printTokens()+"\n" + Player2.printTokens());
        System.out.println(finalString);
    }
}
