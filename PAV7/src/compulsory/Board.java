package compulsory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clasa ce tine initial toti tokenii dintr-un joc in interiorul unei liste synchronized
 * creaaza tokenii dupa parametrii indicati in constructor
 */
public class Board {
    private List<Token> tokens;


    public Board(int n) {
        tokens = Collections.synchronizedList(new ArrayList<>());
        for(int index= 0 ;index<n;index++) {
            tokens.add(new Token(n));
        }
    }

    /**
     * construieste un string ce contine sub o forma toti tokenii existenti inca in board
     * @return stringul rezultat
     */
    public String getPrintTokens(){
        StringBuilder toBePrinted = new StringBuilder("Tokens: ");
        for(Token token:tokens){
            if(token.isWildCard()){
               toBePrinted.append("@ ");
            }
            else{
                toBePrinted.append(token.getNumber()).append(" ");
            }
        }
        return toBePrinted.toString();
    }
    public boolean isEmpty(){
        return tokens.isEmpty();
    }

    /**
     * Verifica validitatea indexului aruncand si o exceptie si dupa sterge un token ce va fi si returnat la player/game
     * @param index locatia de unde se ia tokenul
     * @return returneaza tokenul eliminat din lista pentru a fi ulterior folosit de catre player/game
     * @throws Exception exceptia a fost folosita pentru testing , mai exact pentru a vedea daca threadurile pot oricum acesa un index invalid chiar daca lista e synchronized
     */
    public synchronized Token getToken(int index) throws Exception {

        Token token;
        if(index>tokens.size()-1 || index<0){
            throw new Exception("Invalid index");
        }
        token = tokens.get(index);
        tokens.remove(index);
        return token;
    }
}
