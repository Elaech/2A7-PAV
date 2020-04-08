package compulsory;

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
     * Creaza o noua linie in tabela Artists cu datele primite ca input
     * @param name numele artistului ce trebuie inserat
     * @param country tara artistului ce trebuie inserat
     * @throws SQLException cazul in care inputul nu este valid trebuie semnalat mai sus in program
     */
    public void create(String name, String country) throws SQLException {
        sqlquerry="INSERT INTO ARTISTS(name,country) VALUES(?,?)";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,name);
        ps.setString(2,country);
        ps.executeUpdate();
    }

    /**
     * Cauta un artist dupa nume si returneaza id-ul acestuia,
     * daca sunt mai multi il va returna pe primul
     * @param name numele artistului caruia sa ii cautam ID-ul
     * @return returneaza id-ul artistului
     * @throws SQLException cazul in care inputul nu este valid trebuie semnalat mai sus in program
     */
    public int findByName(String name) throws SQLException {
        sqlquerry="SELECT ID FROM ARTISTS WHERE name=?";
        ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
        ps.setString(1,name);
        rs = ps.executeQuery();
        rs.next();
        int result =rs.getInt(1);
        rs.close();
        return result;
    }
}
