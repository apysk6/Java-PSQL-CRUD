import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    String login;
    String password;
    String dbName;
    String hostName;

    public Connector(String login, String password, String dbName, String hostName) {
        this.login = login;
        this.password = password;
        this.dbName = dbName;
        this.hostName = hostName;
    }

    public int Connect() {
        String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            c = DriverManager.getConnection(urlBuild, login, password);
            return 1;


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }


    }
}
