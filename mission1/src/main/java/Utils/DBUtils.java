package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	public static final String DRIVER_URL = "org.mariadb.jdbc.Driver";
	public static final String URL = "jdbc:mariadb://localhost:3306/wifi_db";
	public static final String DB_USER_ID = "wifi";
	public static final String DB_PASSWORD = "1234";

	public static void DBDriverLoad() {
		try {
			Class.forName(DRIVER_URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		DBDriverLoad();
        return DriverManager.getConnection(URL, DB_USER_ID, DB_PASSWORD);
    }

}
