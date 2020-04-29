package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clasa Client Thread ce deserveste un client pe un thread separat de cel principal
 */
public class ClientThread extends Thread{
    private Socket socket = null ;
    public ClientThread(Socket socket) { this.socket = socket ; }
    public void run () {
        try {
            //initializare conexiune cu client
            BufferedReader clientOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientInput = new PrintWriter(socket.getOutputStream(),true);
            //variabila pentru comunicare
            String request = null;
            clientInput.println("Connected!");
            while(true) {
                //preluam comanda de la client
                request = clientOutput.readLine();
                //daca comadna este exit atunci trimitem un semnal catre client
                // si incheiem conexiunea cu acesta
                if (request.equals("exit")) {
                    clientInput.println("Server stopped");
                    break;
                }
                //daca este orice altceva trimitem catre client mesajul respectiv
                else {
                    clientInput.println("Server received the request...");
                }
            }
        } catch (IOException e) {
            //cazul in care clientul se deconecteaza fortat
            System.out.println(e.getMessage());
        } finally {
            //inchiderea conexiunii cu clientul
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
