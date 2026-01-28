package M5_Activity3;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class PostgreConnection {
	public static Connection connection = null;

	protected static final String URL = "jdbc:postgresql://localhost:5432/training_db";
	protected static final String USER = "postgres";
	protected static final String PASSWORD = "postgres";

	public static void getDBConnection() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

}
