package compulsory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * clasa singleton ce se ocupa de conexiunea la baza de date
 */
public class Database {
    //instanta unica
    static private Database database;
    //conexiunea
    private Connection connection;
    //metoda de preluare a conexiunii
    public Connection getConnection() {
        return connection;
    }
    //constructor inacesibil din exterior
    private Database() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","padba","pasql");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    //metoda spre exterior de preluare a instantei
    public static Database getInstance() throws SQLException {
        if(database == null)
            database= new Database();
        else if (database.getConnection().isClosed()){
            database = new Database();
        }
        return database;
    }
}
