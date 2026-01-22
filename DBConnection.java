//M5_Activity2
package M5_Activity_2;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/training_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
	public static void main(String[] args) {
		
		Connection connection=null;
		//Statement statement=null;
		//ResultSet resultSet=null;
			
			try {
				connection=DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connected to PostgreSQLs successfully!");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

	}

}
