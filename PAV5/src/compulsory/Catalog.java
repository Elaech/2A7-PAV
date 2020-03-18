package compulsory;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.print.Doc;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Catalog {
    private List<Document> entries = new LinkedList<>();

    /**
     * adds documents but tests if their name appears or not
     * @param documents - documents to be added
     */
    public void addEntry(Document... documents) {
        if(entries.stream()
                .map(entry -> entry.getName())
                .anyMatch(
                        Arrays.stream(documents)
                        .map(doc -> doc.getName())
                        .collect(toSet())
                        ::contains
                )){
            System.out.println("Name of one of the files already in the doc entries");
        }
        else {
            Collections.addAll(entries,documents);
        }
    }

    /**
     * Saves the Catalog to an existent file
     * Using the method from serialised objects output streams
     * We only write the path and tags because the id and name are generated
     * to be the same as in the initial file
     * @param path - path to the location of the file in which to save the object
     * Treated the cases of invalid path and of invalid writing parameters of the path
     */
    public void save(String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(entries.size());
            for(Document entry:entries){
                objectOutputStream.writeObject(entry.getPath().toString());
                objectOutputStream.writeObject(entry.getTags());
            }
            objectOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File path non-existent!");
        } catch (IOException e) {
            System.out.println("ONE OF THE FOLLOWING HAPPENED:\n"+
                    "Writing to a network file and got disconnected. \n" +
                    "Writing to a local file that was no longer available. \n" +
                    "Using some stream to write data and some other process closed the stream. \n" +
                    "Trying to write a file but don't have permission. \n" +
                    "Trying to write to a file but disk space was no longer available. ");
        }
    }

    /**
     * Loads a catalog overwriting the current one with the documents
     * stored in the serialised object file
     * We generate the id and name using the constructor from Document class
     * since they will be the same
     * @param path - the path to the file from which we load the catalog
     * @throws ClassNotFoundException - this exception comes from wrongfully starting the application so it must be passed down
     * Treated the cases of invalid path and of invalid reading parameters of the path
     */
    public void load(String path) throws ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            entries.removeAll(entries);
            int size= objectInputStream.readInt();
            for(int index=0;index<size;index++){
                String docPath =(String) objectInputStream.readObject();
                Map<String,String> docTags = (HashMap<String, String>)objectInputStream.readObject();
                Document document = new Document(docPath);
                for (Map.Entry<String, String> pair : docTags.entrySet()) {
                    document.addTag(pair.getKey(), pair.getValue());
                }
                entries.add(document);
            }
            fileInputStream.close();
        } catch (InvalidArgumentException | FileNotFoundException e) {
            System.out.println("File path non-existent!");
        } catch (IOException e) {
            System.out.println("ONE OF THE FOLLOWING HAPPENED:\n"+
                    "Reading a network file and got disconnected. \n" +
                    "Reading a local file that was no longer available. \n" +
                    "Using some stream to read data and some other process closed the stream. \n" +
                    "Trying to read a file but don't have permission.");
        }
    }

    /**
     * Check if OS supports Desktop class
     * Find the first document with the specified name (unique name so only one)
     * Open the document for editing
     * Treated the cases of invalid path and of invalid writing/reading parameters of the path
     */

    public void view(String name){
        if(Desktop.isDesktopSupported()){
            Path doc;
            doc =  entries.stream().filter(entry -> entry.getName().equals(name))
                    .findFirst()
                    .map(Document::getPath)
                    .get();
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.edit(doc.toFile());
            } catch (IOException e) {
                System.out.println("ONE OF THE FOLLOWING HAPPENED:\n"+
                        "Reading a network file and got disconnected. \n" +
                        "Reading a local file that was no longer available. \n" +
                        "Using some stream to read data and some other process closed the stream. \n" +
                        "Trying to read a file but don't have permission.");
            }
        }
        else{
            System.out.println("Desktop class is not supported on this platform");
        }
    }

    /**
     * Same save as above but it writes the objects in a plain text format
     * it puts one parameter per line so it is easier to rebuild after
     * @param path - path to the location of the file in which to save the object
     */
    public void savePlainText(String path){
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            writer.write(entries.size()+"\n");
            for(Document document:entries){
                writer.write(document.getPath().toAbsolutePath().toString()+"\n");
                writer.write(document.getTags().size()+"\n");
                for(Map.Entry<String,String> entry: document.getTags().entrySet()){
                    writer.write(entry.getKey()+"\n"+entry.getValue()+"\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File path non-existent!");
        } catch (IOException e) {
            System.out.println("ONE OF THE FOLLOWING HAPPENED:\n"+
                    "Writing to a network file and got disconnected. \n" +
                    "Writing to a local file that was no longer available. \n" +
                    "Using some stream to write data and some other process closed the stream. \n" +
                    "Trying to write a file but don't have permission. \n" +
                    "Trying to write to a file but disk space was no longer available. ");
        }
    }

    /**
     * Same load as before that uses the Document constructor to generate the name and id
     * but now it creates the catalog from plain text using function readline()
     * @param path the path to the file from which we load the catalog
     */
    public void loadPlainText(String path){
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            Catalog newCatalog = new Catalog();
            int sizeOfCatalog = Integer.parseInt(reader.readLine());
            entries.removeAll(entries);
            for(int index=0;index<sizeOfCatalog;index++){
                Document document = new Document(reader.readLine());
                int sizeOfTags = Integer.parseInt(reader.readLine());
                for(int index1=0;index1<sizeOfTags;index1++){
                    document.addTag(
                            reader.readLine(),
                            reader.readLine()
                    );
                }
                entries.add(document);
            }

            reader.close();
        } catch (InvalidArgumentException | FileNotFoundException e) {
            System.out.println("File path non-existent!");
        } catch (IOException e) {
            System.out.println("ONE OF THE FOLLOWING HAPPENED:\n"+
                    "Reading a network file and got disconnected. \n" +
                    "Reading a local file that was no longer available. \n" +
                    "Using some stream to read data and some other process closed the stream. \n" +
                    "Trying to read a file but don't have permission.");
        }
    }
    public List<Document> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "entries=" + entries +
                '}';
    }
}
