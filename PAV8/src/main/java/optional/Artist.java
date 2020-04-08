package optional;

/**
 * Modeleaza artistul din problema descrisa in laborator
 * nu poate fi instantiat fara o tara si un nume
 */
public class Artist {
    private String name;
    private String country;
    private int id;
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }


    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
