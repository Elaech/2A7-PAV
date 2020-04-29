package optional;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * clasa server ce se ocupa cu gestionarea conexiunilor asupra jocului
 * daca jocul se termina jucatorii sunt deconectati si se poate juca din nou
 */
public class GameServer {
    //initializare joc
    private static final int PORT = 3737;
    private volatile Game game=new Game();
    public GameServer() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            //initializare port
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.flush();
                //daca se ajunge la numar 0 de playeri se reseteaza jocul pentru a putea accepta clienti sa joace la infinit
                if(game.getNrOfPlayers() == 0){
                    game = new Game();
                }
                //pentru fiecare jucator vom face un thread ce are acces la jocul actual si la socketul de comunicare
                new ClientThread(socket,game).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}

