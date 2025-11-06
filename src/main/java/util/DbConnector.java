package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private static final String JDBC_URL = "jdbc:oracle:thin:@odekakedb_medium?TNS_ADMIN=D:/eclipse/workspace/Odekake/src/main/resources/wallet";
    private static final String USERNAME = "ADMIN";
    private static final String PASSWORD = "koreGApassw0rd";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}