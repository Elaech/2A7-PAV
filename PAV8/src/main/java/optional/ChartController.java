package optional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Controllerul chartului
 * operatiile ce pot fi facute sunt de updata topul in baza de date cu artistii noi aparuti cu valori random de popularitate
 * si de a returna o lista cu chartul de artisti
 */
public class ChartController {
    /**
     * lista cu artistii ce nu sunt in top chart inca
     */
    List<Integer> artistsID = new ArrayList<>();

    /**
     * Metoda ce cauta in baza de date artistii ce nu sunt in top chart pentru ai trece cu valori random in acesta
     */
    public void updateChart() {
        artistsID.removeAll(artistsID);
        try {
            //preluam artistii ce nu se gasesc in top
            String sqlquerry = "(SELECT ID FROM ARTISTS)MINUS(SELECT ARTIST_ID FROM CHART)";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
            ResultSet rs = ps.executeQuery();
            //ii adaugam la lista cu artistii ce nu sunt in top
            while (rs.next()) {
                artistsID.add(rs.getInt(1));
            }
            //inchidem statementul si cursorul
            rs.close();
            ps.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            Database.getInstance().getConnection().close();
        }catch (Exception ignored){}
        //apelam metoda ce insereaza pentru id-urile gasite noi intrari in chart
        insertPopularity();

    }

    /**
     * metoda ce insereaza pentru artistii regasiti in lista cu id-uri ale clasei valori in cadrul top chartului
     */
    private void insertPopularity(){
        try{
        if(!artistsID.isEmpty()) {
            //cream un multiple insert querry tipic oracle
            String sqlquerry = "INSERT ALL";
            for (Integer id : artistsID) {
                sqlquerry += " INTO CHART(ARTIST_ID,POPULARITY) VALUES";
                sqlquerry += "(" + (int)id + "," + (int) (Math.random() * 10000) + ")";
            }
            sqlquerry += "SELECT * FROM DUAL";
            //la final il executam si inchidem cursorul
            Statement s = Database.getInstance().getConnection().createStatement();
            s.executeUpdate(sqlquerry);
            s.close();
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            //din cauza ca aceasta operatie nu este facuta des ne permitem sa inchidem conexiunea cu baza de date
        }finally {
            try{Database.getInstance().getConnection().close();}catch (Exception ignored){};
        }

    }

    /**
     * metoda ce ia din baza de date artistii in ordinea din top si ii returneaza sub forma de lista
     * @return lista cu artistii din top in ordine
     */
    public List<Artist> getChart(){
        List<Artist> artists = new ArrayList<>();
        try {
            //luam informatiile artistilor din top
            String sqlquerry = "SELECT A.ID,A.NAME,A.COUNTRY,C.POPULARITY FROM ARTISTS A JOIN CHART C ON A.ID=C.ARTIST_ID ORDER BY C.POPULARITY DESC";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sqlquerry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //pentru fiecare row creeam un artist si il inseram in lista
                Artist artist = new Artist(rs.getString(2), rs.getString(3));
                artist.setId(rs.getInt(1));
                artist.setPopularity(rs.getInt(4));
                artists.add(artist);
            }
            //inchidem statementul si cursorul
            ps.close();
            rs.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            //din cauza ca aceasta operatie nu este executata des ne permitem sa inchidem conexiunea
        }finally {
            try{Database.getInstance().getConnection().close();}catch (Exception ignored){};
        }
        return artists;
    }
}
