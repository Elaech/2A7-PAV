package optional;

/**
 * Modeleaza Albumul din contextul problemei din laborator
 * nu poate fi instantiat fara un ID de artist , an si nume
 */
public class Album {
    private String name;
    private int year;
    private int id;
    private int artistID;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public Album(String name, int year, int artistID) {
        this.name = name;
        this.year = year;
        this.artistID = artistID;
    }
}
