package optional;

/**
 * aici nu sunt decat apeluri pentru a incepe jocus
 * sunt tot felul de cazuri testate de mine care le-am facut sa mearga
 * fiecare caz are cate ceva special de observat
 */
public class MainOptional {
    public static void main(String[] args) {
        //test pentru a depasi timerul de 1 secuda - player random
        //Game game = new Game("P1",1,"P2",1,10000,100,1);

        //test pentru a vedea ca timerul se icheie cand jocul e gata - player random
        //Game game = new Game("P1",1,"P2",1,10,3,3000);

        //test mic pentru verificare functionalitate - player random (asta e default)
        Game game = new Game("P1",1,"P2",1,10,3,1);

        //test player manual human vs computer random
       // Game game = new Game("Human",2,"Computer",1,10,3,100);

        //test player human vs human
        //Game game = new Game("Human1",2,"Human2",2,10,3,100);

        //test player smart vs human
        //Game game = new Game("Human1",2,"ComputerSmart",3,10,3,100);

        //test computer dumb vs smart
        //Game game = new Game("Computer",1,"ComputerSmart",3,10,3,100);
        game.start();
    }
}
