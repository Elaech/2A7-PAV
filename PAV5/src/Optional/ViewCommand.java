package Optional;

import compulsory.Catalog;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * for a given document name loaded from the catalog the user will get a pop-up
 * for edditing the document
 * Treated the not know name error
 */
public class ViewCommand implements Command {

    public static void startCommand(Catalog catalog, Scanner scanner) {

        System.out.println("Give name of the file");
        String name = scanner.next();
        List<String> names = catalog.getEntries().stream()
                .map(entry -> entry.getName())
                .collect(toList());
        if(!names.contains(name)){
            System.out.println("Name not found");
        }
        else {
            catalog.view(name);
        }
    }
}
