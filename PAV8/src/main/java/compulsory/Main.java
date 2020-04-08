package compulsory;
import java.sql.*;


/**
 * clasa in care se exemplifica lucrul cu clasele ce acceseaza baza de date
 */
public class Main {
    public static void main(String[] args) {
        try {
            //creeam cele 2 controllere
            ArtistController artistController = new ArtistController();
            AlbumController albumController = new AlbumController();

            //creeam 2 artisti
            String artistName1 = "Rikki";
            String artistCountry1 = "Guatemala";

            String artistName2 = "Timmy";
            String artistCountry2 = "Spain";

            //inseram in baza de date cei 2 artisti
            artistController.create(artistName1, artistCountry1);
            artistController.create(artistName2, artistCountry2);

            //inseram cate un album pentru fiecare luand id-ul cu ajutorul metodei din artist controller
            albumController.create("Nothing",artistController.findByName(artistName1),2001);
            albumController.create("Something",artistController.findByName(artistName2),2011);

            //luam lista cu albumele lui Rikki si Timmy
            albumController.findByArtist(artistController.findByName(artistName1));
            albumController.findByArtist(artistController.findByName(artistName2));

        }
        //daca am cauzat o eroare in baza de date o afisam
        catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        //inchidem conexiunea cu baza de date
        finally {
            try{Database.getInstance().getConnection().close();} catch (Exception ignored) {}
        }
    }
}