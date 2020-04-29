package compulsory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * clasa ce accepta clienti si creaza cate un un Thread pentru fiecare
 */
public class GameServer {
    private static final int PORT = 3737;
    public GameServer() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            //crearea socketului
            serverSocket = new ServerSocket(PORT);
            while (true) {
                //acceptarea si creerea de noi threads
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}

