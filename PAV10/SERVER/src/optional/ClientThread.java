package optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clasa ClientThread ce va servi un client pe un thread separat de cel principal
 */
public class ClientThread extends Thread {
    private Socket socket = null;
    private volatile Game game;
    private Player player;

    public ClientThread(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }

    public void run() {
        try {
            //initializare comunicare cu clientul
            BufferedReader clientOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientInput = new PrintWriter(socket.getOutputStream(), true);
            String request = null;

            //daca jocul nu este in desfasurare cu alti 2 playeri acesta va returna o pozitie de player pentru acest client
            player = game.getPlayerIfNotFull();
            if (player != null) {
                //anuntam clientul ca va juca, si va marca poziitle cu semnul lui (x sau o)
                clientInput.println("Welcome to the game| You will use " + player.getMark());
                //afisam bordul initial
                clientInput.println(game.getBoard());
                //cat timp jocul nu este terminat: cineva se deconecteaza, nu a castigat nimeni, se mai asteapta jucatori
                while (!game.isFinished()) {
                    //preluam comanda data de jucator
                    //comanda data de player poate fi:
                    // exit: termina jocul si deconecteaza ambii clienti
                    request = clientOutput.readLine();
                    if (game.isFinished())
                        break;
                    if (request.equals("exit")) {
                        game.setFinished(player.getOrder());
                        break;
                    }
                    // showtable: arata situatia actuala a tabelei
                    else if(request.equals("showtable")){
                        clientInput.println(game.getBoard());
                    }
                    // myturn: true/false daca este tura lui la mutare
                    else if(request.equals("myturn")){
                        clientInput.println(game.getOrder() == player.getOrder());
                    }
                    // mark x y: marcheaza tabela la linia x col. y cu simbolul playerului, trimite si tabela in caz de reusita
                    // ^mark x y: nu functioneaza daca nu este tura playerului respectiv
                    // ^mark x y: este validat si in caz de input incorect se va anunta jucatorul
                    else if (game.getOrder() == player.getOrder()) {
                        if(game.isValidMove(request)){
                            game.turn(player,request);
                            clientInput.println("You did your turn NICE!");
                            clientInput.println(game.getBoard());
                        }
                        else {
                            clientInput.println("Invalid move! Try \"mark x y\" , they both need to be in [1,16] and the position must be empty");
                        }
                    } else {
                        clientInput.println("Wait your turn ;)");
                    }
                }
                //cand se termina jocul avem grija sa instiintam jocul de parasirea jucatorului
                game.playerLeft();
                //verificam stadiul de terminare pentru clientul ce este servit de thread
                //acesta poate fi:
                //stadiul de castigare, a facut 5 simboluri adiacente in diagonala,vertocala sau orizontala in tabela jocului
                if (game.getWinner() == player.getOrder()) {
                    clientInput.println("You have won! Congratulations!");
                } else {
                    //stadiul de abandon, jucatorul trimite comanda exit in timpul jocului, ceea ce va termina jocul
                    if (game.getDisconnected()== player.getOrder()) {
                        clientInput.println("You disconnected >.<");
                    }
                    //stadiul de abandon a celuilalt jucator
                    else if(game.getDisconnected() < 2){
                        clientInput.println("The other player disconnected Q.Q");
                    }
                    //stadiul de pierdere, jocul a fost castigat de celalalt client
                    else {
                        clientInput.println("You have failed! Get them next time!");
                    }
                }
            } else {
                //daca jocul este plin anuntam clientul ce dorea conexiunea
                clientInput.println("Game is full! Try later...");
            }
        } catch (IOException e) {
            //cazul in care se produc erori de comunicare cu clientul (acesta se deconecteaza fortat, pica netu etc...)
            System.out.println(e.getMessage());
        } finally {
            try {
                //inchiderea comunicarii cu clientul
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
