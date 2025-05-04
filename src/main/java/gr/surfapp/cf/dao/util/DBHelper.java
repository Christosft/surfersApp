package gr.surfapp.cf.dao.util;

import gr.surfapp.cf.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for database operations. Specifically, this class provides functionality to erase data from all tables
 * in the database except for the 'surfspots' table. It also handles disabling and enabling foreign key checks.
 *
 * This class cannot be instantiated as it contains only static methods.
 */



public class DBHelper {

    /**
     * No instances of this class should be available
     *
     */

    private DBHelper() {

    }

    public static void eraseData() throws SQLException {
        String sqlFKOff = "SET @@foreign_key_checks = 0";

        String sqlFKOn = "SET @@foreign_key_checks = 1";

        String sqlSelect = "SELECT TABLE_NAME FROM information_schema.tables WHERE TABLE_SCHEMA = 'surfsessionsdbstaging'";
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection(); PreparedStatement ps1 = connection.prepareStatement(sqlFKOff);
             PreparedStatement ps2 = connection.prepareStatement(sqlSelect)) {

            ps1.executeUpdate();
            rs = ps2.executeQuery();
            List<String> tables = mapRsToList(rs);

            for (String nameTable : tables) {
                if (!nameTable.equals("surfspots")) {
                    connection.prepareStatement("DELETE FROM " + nameTable + " AUTO_INCREMENT=1").executeUpdate();
                }
            }

            connection.prepareStatement(sqlFKOn).executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        }
    }

    private static List<String> mapRsToList(ResultSet rs) throws SQLException {

        List<String> tables = new ArrayList<>();

        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
    }
        return tables;
    }

}

