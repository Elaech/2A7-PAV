package optional;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * clasa DAO ce se ocupa cu operatiile din baza de date asupra tabelului Albums
 */
public class AlbumController {
    private String sqlquerry;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * creeaza o intrare in tabelul albums
     * @param album o clasa album completata cu informatiile ce trebuie inserate pentru noua intrare in tabel
     * @throws SQLException cazul in care datele primite in album trebuie semnalat exterior
     */
    public void create(Album album) throws SQLException {
        sqlquerry="INSERT INTO ALBUMS(name,artist_id,release_year) VALUES(?,?,?)";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,album.getName());
        ps.setInt(2,album.getArtistID());
        ps.setInt(3,album.getYear());
        ps.executeUpdate();
        //inchidere statement
        ps.close();
    }

    /**
     * metoda ce cauta si afiseaza albumele unui artist
     * @param artistID id-ul artistului dupa care se cauta
     * @throws SQLException cazul in care inputul este invalid trebuie semnalat exterior
     */
    public void findByArtist(int artistID) throws SQLException {
        System.out.println("Albums of artist with id "+artistID);
        sqlquerry="SELECT * FROM ALBUMS WHERE artist_id=?";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setInt(1,artistID);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("Album: " + rs.getString(2) + " ID: " + rs.getInt(1) + " Year: " + rs.getInt(4));
        }
        //inchidem cursorul si statementul
        ps.close();
        rs.close();
    }
}
