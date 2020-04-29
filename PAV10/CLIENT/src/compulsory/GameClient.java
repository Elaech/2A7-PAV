package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;

/**
 * Clasa Game client ce se conecteaza la serverul jocului
 * trateaza cazurile speciale de input si output catre server
 *
 * A se tine cont ca este nevoie de minim 2 procese de acest tip pentru a testa jocul, deoarece acesta se joaca in 2 pe runde
 * Pentru a testa cazul cu "game is full" mai trebuie deschis un al treilea client in timp ce primii 2 joaca
 *
 *
 * De mentionat este ca am suprascris nedandu-mi seama clientul compulsory care doar prelua date si le trimitea la server
 * si pe urma astepta un raspuns pe care il afisa la consola inapoi la user
 */
public class GameClient {
    //adresa serverului
    private static final String serverAddress = "127.0.0.1";
    private static final int PORT = 3737;

    //toata logica clientului
    public GameClient() throws IOException {
        Socket socket = null;
        try {
            //initializare conexiune, input si ouput server
            socket = new Socket(serverAddress, PORT);
            PrintWriter serverInput = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //initializare input consola locala client
            BufferedReader clientOutput = new BufferedReader(new InputStreamReader(System.in));
            //variabile folosite in comunicarea server-client
            String request = null;
            String response = null;

            //primul mesaj de la server ce ne anunta daca avem loc sau nu sa jucam (doar 1 camera de joc)
            response = serverOutput.readLine();
            System.out.println(response);
            if (!response.equals("Game is full! Try later...")) {
                //afisam tabla de joc
                System.out.println("Game board:");
                for (int i = 0; i < 17; i++) {
                    response = serverOutput.readLine();
                    if (response != null)
                        System.out.println(response);
                }
                //cat timp serverul nu ii va comunica clientului unul din mesajele de incheiere acesta poate trimite mesaje
                while (true) {
                    //luam comanda de la consola si o transmitem serverului
                    request = clientOutput.readLine();
                    serverInput.println(request);
                    //cazul in care vrem sa vedem tabela actuala
                    if (request.equals("showtable")) {
                        System.out.println("Game board:");
                        for (int i = 0; i < 17; i++) {
                            response = serverOutput.readLine();
                            if (response != null)
                                System.out.println(response);
                        }
                    } else {
                        //preluam rezultatul comenzii
                        response = serverOutput.readLine();
                        System.out.println(response);
                        //daca este un mesaj de incheiere a jocului
                        if (response.equals("Server stopped") || response.equals("You have failed! Get them next time!")
                                || response.equals("You have won! Congratulations!") || response.equals("You disconnected >.<")
                                || response.equals("The other player disconnected Q.Q")) {
                            break;
                        }
                        //daca am reusit sa ne facem tura cum trebuie jocul ne va trimite si noua tabela
                        else if (response.equals("You did your turn NICE!")) {
                            System.out.println("Game board:");
                            for (int i = 0; i < 17; i++) {
                                response = serverOutput.readLine();
                                if (response != null)
                                    System.out.println(response);
                            }
                        }
                    }
                }
            }
            //la final un mesaj de incheiere si inchidem descriptorul consolei
            System.out.println("Exiting client...");
            clientOutput.close();
        } catch (UnknownHostException e) {
            //erorile de conexiune la server
            e.printStackTrace();
        }
    }
}