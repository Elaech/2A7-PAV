package optional;

/**
 * Clasa Board se ocupa cu mentinerea bordului, starii acestuia
 * Si cu anumite operatii ce se pot executa pe el
 */
public class Board {
    private char[][] board = new char[16][16];
    private boolean finished = false;

    //bordul este o matrice de 16x16 pozitii ce sunt initial '-'
    //ele pot deveni 'X' sau '0' pana la finalul jocului
    public Board() {
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                board[i][j] = '-';
    }
    //marcheaza pozitia cu simbolul primit
    public void mark(char mark, int x, int y) {
        board[x][y] = mark;
    }
    //returneaza simbolul dintr-o pozitie
    public char getMark(int x, int y) {
        return board[x][y];
    }
    //construieste un output string al tabelei si il returneaza
    public String getBoard() {
        String output = "";
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++)
                output += board[i][j] + " ";
            output += '\n';
        }
        return output;
    }
    public boolean isFinished() {
        return finished;
    }

    /**
     * testeaza daca tabela de joc are o combinatie de pozitii castigatoare
     * caz in care se muta in state-ul finished
     */
    public void checkFinishCondition() {
        //pentru o pozitie x,y vectorii ce pot duce la castig sunt formati din x,y + vectorii de mai jos
        int[] directionsX = {0, 0, -4, 4, -4, 4, -4, 4};
        int[] directionsY = {-4, 4, 0, 0, -4, -4, 4, 4};
        label:
        //verificam toate pozitiile pentru un caz castigator
        for (int i = 0; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                //pentru fiecare pozitie parcurgem vectorii de pozitii posibile castigatoare ale ei
                for (int index = 0; index < 8; index++) {
                    //verificam ca pozitiile sa fie valide
                    if (inBounds(i, j, i + directionsX[index], j + directionsY[index])) {
                        //daca sunt toate cele 5 pozitii la fel atunci terminam jocul
                        if (allTheSame(i, j, i + directionsX[index], j + directionsY[index])) {
                            finished = true;
                            break label;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * Verifica daca intre punctele (x1,y1) si (x2,y2) nu exista decat un singur simbol la fel
     */
    public boolean allTheSame(int x1, int y1, int x2, int y2) {
        //testam doar daca nu este o pozitie goala
        char mark = board[x1][y1];
        if(mark == '-')
            return false;
        //daca pozitiile de pe aceleasi axe sunt la fel nu are rost sa modificam pozitia respectiva
        int incrementX = x1==x2? 0:1;
        int incrementY= y1==y2?0:1;
        //daca coordonatele sunt in ordine descrescatoare vom inmulti cresterea cu -1
        if(x2<x1)
            incrementX*=-1;
        if(y2<y1)
            incrementY*=-1;

        //mergem intre cele 2 puncte si verificam cu simbolul initial
        for(int i=1;i<5;i++){
            if(mark!=board[x1+i*incrementX][y1+i*incrementY])
                return false;
        }

        return true;
    }
    //verifica ca cele 2 pozitii din tabela sa fie valide
    public boolean inBounds(int x1, int y1, int x2, int y2) {
        return (x1 < 16 && x1 >= 0) && (x2 < 16 && x2 >= 0) && (y1 < 16 && y1 >= 0) && (y2 < 16 && y2 >= 0);
    }
}
