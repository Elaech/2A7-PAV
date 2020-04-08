package optional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * clasa DAO ce se ocupa cu operatiile in baza de date asupra tabelului Artists
 */
public class ArtistController {
    private String sqlquerry;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creaza o noua intrare in tabelul artists dupa un input
     * @param artist noul artist ce trebuie inserat
     * @throws SQLException exceptie cauzata de parametrii din input gresiti
     */
    public void create(Artist artist) throws SQLException {
        sqlquerry="INSERT INTO ARTISTS(name,country) VALUES(?,?)";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,artist.getName());
        ps.setString(2,artist.getCountry());
        ps.executeUpdate();
        ps.close();
    }

    /**
     * gaseste id-ul unui artist dupa numele acestuia si returneaza primul artist gasit
     * @param name numele artistului
     * @return ID-ul artistului cautat
     * @throws SQLException cazul in care inputul primit este gresit trebuie semnalat exterior
     */
    public int findByName(String name) throws SQLException {
        sqlquerry="SELECT ID FROM ARTISTS WHERE name=?";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,name);
        rs = ps.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        ps.close();
        return id;
    }
}
