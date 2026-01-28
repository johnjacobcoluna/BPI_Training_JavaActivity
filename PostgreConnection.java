package M5_Activity3;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class PostgreConnection {
	public static Connection connection = null;

	public static final String URL = "jdbc:postgresql://localhost:5432/training_db";
	public static final String USER = "postgres";
	public static final String PASSWORD = "postgres";
}
