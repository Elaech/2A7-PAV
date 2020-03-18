package Optional;

import compulsory.Catalog;
import compulsory.Document;

import java.util.Scanner;

/**
 * For each document present in the catalog we write some headers and paragraphs
 * for details in a html file and print it to the user
 */
public class RepHTMLCommand implements Command {
    public static void startCommand(Catalog catalog){
        System.out.println("<!DOCTYPE HTML>");
        System.out.println("<html>");
        System.out.println("<head>");
        System.out.println("<title>Catalog</title>");
        System.out.println("</head>");
        System.out.println("<body>");
        for(Document document:catalog.getEntries()){
            System.out.println("<h1>"+document.getName()+"</h1><br>");
            System.out.println("<p>Located at: "+document.getPath()+"</p><br>");
            System.out.println("<p>With id: "+document.getId()+"</p><br>");
            System.out.println("<p>With tags: "+document.getTags()+"</p><br>");
            System.out.println("<br><br><br><br>");
        }
        System.out.println("</body>");
        System.out.println("</html>");
    }
}
