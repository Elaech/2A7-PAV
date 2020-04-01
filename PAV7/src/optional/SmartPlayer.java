package optional;

import java.util.List;

/**
 * Clasa ce implementeaza un player calculator cu o anumita strategie
 */
public class SmartPlayer extends Player {
    public SmartPlayer(String name, Board b, int sizeOfProgression) {
        super(name, b, sizeOfProgression);
    }
    private boolean atLeast2tokensNotWildcards(List<Token> tokens){
        int nr=0;
        for(Token token:tokensAcquired){
            if(!token.isWildCard())
                nr++;
            if(nr==2)
                return true;
        }
        return false;
    }

    /**
     * Aici este strategia folosita
     * Pentru acest joc wildcardurile sunt foarte puternice, deci daca exista ele trebuiesc luate
     * Daca nu mai exista pe board wildcarduri neluate atunci mergem prin tokenii alesi deja:
     * Daca nu avem macar 2 numere in tokeni atunci luam un token random deoarece nu influenteaza
     * Daca avem 2 numere atunci putem vedea diferenta intre acestea si cauta un numar care sa completeze progresia
     * Vom cauta pentru orice 2 numere adiacente din tokenii playerului un token pe board care sa continue progresia
     * Daca nu vom gasi nici un astfel de token vom lua un token random
     * @return pozitia de pe care se ia tokenul
     */
    private int getEstimatePosition(){
        int index = board.getTokenPosByNumber(-1);
        if(index!=-1)
            return index;
        if(atLeast2tokensNotWildcards(tokensAcquired)) {
            Token prevToken = tokensAcquired.get(0);
            boolean prevSelected = false;
            for (Token token : tokensAcquired) {
                if (!token.isWildCard()) {
                    if(!prevSelected){
                        prevToken = token;
                        prevSelected = true;
                    }
                    else {
                        index = board.getTokenPosByNumber(2*token.getNumber()-prevToken.getNumber());
                        if(index== -1){
                            index = board.getTokenPosByNumber(2*prevToken.getNumber()-token.getNumber());
                            if(index!=-1){
                                return index;
                            }
                        }
                        else
                            return index;
                        prevToken = token;
                    }
                }
            }
        }
        return (int) (Math.random()*(board.getSize()-1));
    }
    @Override
    public void run() {
        while (!board.isFinished()) {
            addToken((int) (getEstimatePosition()));
        }
        terminated = true;
    }
}
