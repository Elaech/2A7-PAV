package Optional;

import compulsory.Catalog;
import compulsory.Document;

/**
 * List the documents present in the currently loaded catalog
 */

public class ListCommand implements Command  {
    public static void startCommand(Catalog catalog){
        if(catalog.getEntries().size()==0){
            System.out.println("Nothing to list");
        }
        for(Document document:catalog.getEntries()){
            System.out.println("#####################");
            System.out.println("Name: "+document.getName());
            System.out.println("Path: "+document.getPath());
            System.out.println("#####################");
        }
    }
}
