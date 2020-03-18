package Optional;

import compulsory.Catalog;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * the shell simulates a terminal in which the user can
 */
public class Shell {
    public void start() throws ClassNotFoundException {
        boolean quitCondition = false;
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        String commandString;
        while (!quitCondition){
            commandString = scanner.next();
            switch (commandString){
                case "quit": {
                    quitCondition = true;
                    break;
                }
                case "load":{
                    LoadCommand.startCommand(catalog,scanner);
                    break;
                }
                case "view":{
                    ViewCommand.startCommand(catalog,scanner);
                    break;
                }
                case "list":{
                    ListCommand.startCommand(catalog);
                    break;
                }
                case "html":{
                    RepHTMLCommand.startCommand(catalog);
                    break;
                }
                default: {
                    System.out.println("Unknown Command!");
                    break;
                }
            }
        }
    }
}
