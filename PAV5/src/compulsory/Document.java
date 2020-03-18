package compulsory;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Document {

    private final Path path;
    private final String name;
    private final int id;
    private Map<String,String> tags = new HashMap<>();

    /**
     * Checks existence of file and generates an unique ID using hashcode
     * and takes its name from the path (last part of the path)
     * @param path - path to file
     * @throws InvalidArgumentException - file path is incorrect
     */
    public Document(String path) throws InvalidArgumentException {
        this.path = Paths.get(path);
        if(!Files.exists(this.path)){
            throw new InvalidArgumentException(new String[]{"Path does not exist "+path});
        }
        id = path.hashCode();
        name = this.path.getFileName().toString();
    }

    /**
     * Checks existence of file and generates an unique ID using hashcode
     * and takes its name from the path (last part of the path)
     * @param path - path to file
     * @throws InvalidArgumentException - file path is incorrect
     */
    public Document(Path path) throws InvalidArgumentException {
        this.path = path;
        if(!Files.exists(this.path)){
            throw new InvalidArgumentException(new String[]{"Path does not exist "+path});
        }
        id = this.path.hashCode();
        name = this.path.getFileName().toString();
    }
    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void addTag(String name, String value){
        tags.put(name,value);
    }

    public void removeTag(String name){
        tags.remove(name);
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Document{" +
                "\npath=" + path +
                ", \nname='" + name + '\'' +
                ", \nid=" + id +
                ", \ntags=" + tags +
                "\n}";
    }
}
