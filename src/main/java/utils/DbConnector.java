package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static final Logger logger = Logger.getLogger(DbConnector.class);
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3307/shop" +

            "?verifyServerCertificate=false" +

            "&useSSL=false" +

            "&requireSSL=false" +

            "&useLegacyDatetimeCode=false" +

            "&amp" +

            "&serverTimezone=UTC";
    private static final String login = "root";
    private static final String password = "13frog";

    public static Connection connect() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(DB_URL, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("problem with connection to db", e);
        }
        return null;
    }
}
