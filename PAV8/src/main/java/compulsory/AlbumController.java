package compulsory;


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
     * creaza o noua intrare in tabela Albums cu datele din input
     * @param name numele albumului ce trebuie inserat
     * @param artistID id-ul artistului caruia ii apartine albumul
     * @param realeaseYear anul de lansare a albumului
     * @throws SQLException cazul in care inputul nu este valid trebuie semnalat mai sus in program
     */
    public void create(String name,int artistID,int realeaseYear) throws SQLException {
        sqlquerry="INSERT INTO ALBUMS(name,artist_id,release_year) VALUES(?,?,?)";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,name);
        ps.setInt(2,artistID);
        ps.setInt(3,realeaseYear);
        ps.executeUpdate();
    }

    /**
     * Cauta dupa ID artistului primit ca input albumele acestuia si le afiseaza in consola
     * @param artistID id-ul artistului dupa care sa caute albumele
     * @throws SQLException cazul in care inputul nu este valid trebuie semnalat mai sus in program
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
        rs.close();
    }
}
