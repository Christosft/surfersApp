package gr.surfapp.cf.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/surf_Sessions_db?serverTimezone=UTC");
        ds.setUsername("surfersSes");
        ds.setPassword(System.getenv("PASSWD_SURFSES"));

        ds.setInitialSize(10);
        ds.setMinIdle(10);
        ds.setMaxIdle(15);
    }

    private DBUtil() {

    }

    public static Connection getConnection() throws SQLException {
        connection = ds.getConnection();

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }
}
