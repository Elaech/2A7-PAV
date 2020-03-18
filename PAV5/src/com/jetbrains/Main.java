package com.jetbrains;

import com.sun.javaws.exceptions.InvalidArgumentException;
import compulsory.Catalog;
import compulsory.Document;


public class Main {

    /**
     * The example for Lab5
     * Treated the Exceptions that needed to be treated (file non existent, IO failed etc)
     * Did not treat app-threatening exceptions
     * @param args
     * @throws ClassNotFoundException - wrongfully starting the application so the app must not continue further
     */
    public static void main(String[] args) throws ClassNotFoundException {
        //try block in case we added wrong paths
        try {
            //Create 3 random documents to add to the catalog
            Document document1 = new Document("src/com/jetbrains/Main.java");
            Document document2 = new Document("src/compulsory/Catalog.java");
            Document document3 = new Document("src/compulsory/Document.java");
            document1.addTag("author","Minut Mihai Dimitrie");
            //create the catalog
            Catalog catalog = new Catalog();
            catalog.addEntry(document1,document2,document3);
            System.out.println(catalog);
            //save the catalog using binary serialisation
            catalog.save("src/compulsory/MyCatalog.ser");

            //Test the save with a new catalog
            Catalog newCatalog = new Catalog();
            newCatalog.load("src/compulsory/MyCatalog.ser");
            System.out.println(newCatalog);

            newCatalog.view("Main.java");
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }
}
