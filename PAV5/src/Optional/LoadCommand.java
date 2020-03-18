package Optional;

import com.sun.javaws.exceptions.InvalidArgumentException;
import compulsory.Catalog;
import compulsory.Document;

import java.util.Scanner;

/**
 * Loads a catalog replacing the existing one in the shell
 */
public class LoadCommand implements Command {

    public static void startCommand(Catalog catalog, Scanner scanner) throws ClassNotFoundException {
        System.out.println("Give a path to a file");
        catalog.load(scanner.next());
    }
}
