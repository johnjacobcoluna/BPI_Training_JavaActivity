package M5_Activity3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Students extends PostgreConnection {
	public static String studName;
	public static int studAge;
	public static String studEmail;

	public static Connection connection = null;
	Scanner sc = new Scanner(System.in);
	public static void addStudent(Scanner sc) {

		String INSERT_STUDENT_SQL = "INSERT INTO students (name, age, email) VALUES (?, ?, ?) RETURNING id;";
		System.out.print("Enter Student name: ");
		studName = sc.nextLine();

		System.out.print("Enter age:");
		studAge = sc.nextInt();

		System.out.print("Enter Email:");
		studEmail = sc.next();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement prepstate = connection.prepareStatement(INSERT_STUDENT_SQL);
			prepstate.setString(1, studName);
			prepstate.setInt(2, studAge);
			prepstate.setString(3, studEmail);

			try (ResultSet result = prepstate.executeQuery()) {
				if (result.next()) {
					int id = result.getInt(1);
					System.out.println("Student inserted!");
					System.out.println("ID | Name | Age | Email");
					System.out.printf(id + " | " + studName + " | " + studAge + " | " + studEmail + " | \n");
					connection.close();
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void showStudent() throws SQLException {

		String SELECT_STUDENT_SQL = "SELECT id, name, age, email FROM students";
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(SELECT_STUDENT_SQL);
			ResultSet result = ps.executeQuery();

			ResultSetMetaData meta = result.getMetaData();
			int columnCount = meta.getColumnCount();
			System.out.println("ID | Name | Age | Email");
			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(result.getObject(i) + " | ");
				}
				connection.close();
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
