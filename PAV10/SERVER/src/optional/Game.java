package optional;

/**
 * clasa Game va fi shared de cele 3 threaduri ale serverului: jucator 1,2 si main thread
 * aceasta are variabilele volatile pentru real time data, important pentru synchronizarea mutarilor
 */
public class Game {
    //locurile playerilor ce vor juca
    private volatile Player player1 = null;
    private volatile Player player2 = null;
    //tabla de joc
    private volatile Board board = new Board();
    //numarul de ordine ce urmeaza sa mute/sa se conecteze
    private volatile int order = 0;
    //variabile de control a starii jocului
    private volatile boolean started = false;
    private volatile boolean finished = false;
    // varibile ce se ocupa cu determinarea outcomului jocului
    private volatile int disconnected = 2;
    private volatile int winner = 2;
    //nr de playeri actuali
    private volatile int nrOfPlayers = 0;

    /**
     * Verifica daca jocul mai are locuri libere si creeaza o instanta de player in cazul afirmativ
     * daca ambele locuri sunt ocupate marcheaza jocul ca si inceput
     * @return instanta nou creata a unui player, sau null daca jocul este deja in desfasurare
     */
    public synchronized Player getPlayerIfNotFull() {
        if (player1 == null) {
            player1 = new Player(order, 'X');
            order++;
            nrOfPlayers++;
            return player1;
        } else if (player2 == null) {
            player2 = new Player(order, 'O');
            order = 0;
            nrOfPlayers++;
            started = true;
            return player2;
        }
        return null;
    }

    public int getNrOfPlayers() {
        return nrOfPlayers;
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getOrder() {
        return order;
    }

    public int getWinner() {
        return winner;
    }

    /**
     * seteaza jocul ca si terminat din punct de vedere a playerului cu numarul de ordine primit
     * @param order numarul de ordine a playeruluii ce a exectutat comanda
     */
    public void setFinished(int order) {
        if(!board.isFinished()){
            disconnected = order;
        }
        finished = true;
    }

    public synchronized void playerLeft() {
        nrOfPlayers--;
    }
    public int getDisconnected(){
        return disconnected;
    }

    /**
     * Face mutarea trimisa de player cu simbolul potrivit
     * Modifica stadiul tablei de joc si verifica terminarea acestuia
     * @param player playerul ce face mutarea
     * @param move comanda de mutare trimisa de player
     */
    public void turn(Player player,String move) {
        String[] art = move.split(" ");
        int x=Integer.parseInt(art[1]);
        int y=Integer.parseInt(art[2]);
        board.mark(player.getMark(),x-1,y-1);
        board.checkFinishCondition();
        if(board.isFinished()){
            finished = true;
            winner = order;
        }
        int aux = (order + 1) % 2;
        order = aux;
    }

    public String getBoard() {
        return board.getBoard();
    }

    /**
     * Verifica ca comanda data de player sa fie sub forma "mark x y" unde x,y sunt intre 1 si 16 inclusiv
     * si pozitia sa fie una libera, marcata cu '-' simbol initial
     * @param move comanda data de player
     * @return true comanda valida, false comanda invalid
     */
    public boolean isValidMove(String move){
        String[] art = move.split(" ");
        if(!art[0].equals("mark")){
            return false;
        }
        int x,y;
        try {
           x=Integer.parseInt(art[1]);
           y=Integer.parseInt(art[2]);
           if(0<x && x<17 && y>0 && 17>y){
               if(board.getMark(x-1,y-1) == '-') {
                   //pozitie valida si libera
                   return true;
               }
               else {
                   //pozitie ocupata
                   return false;
               }
           }
        }catch (Exception e){
            //cazul in care nu se trimit numere ca parametri
            return false;
        }
        //daca pozitia este invalida
        return false;
    }
}
