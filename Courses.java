package M5_Activity3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Courses extends PostgreConnection {
	public static int studID;
	public static String course;
	public static double grade;

	private static Connection connection = null;

	public static void addCourse(Scanner sc) {
		// connection = DriverManager.getConnection(URL, USER, PASSWORD);

		String INSERT_STUDENT_SQL = "INSERT INTO courses (student_id, course_name, grade) VALUES (?, ?, ?) RETURNING id;";
		System.out.print("Enter Student ID: ");
		studID = sc.nextInt();

		System.out.print("Enter course:");
		course = sc.next();

		System.out.print("Enter Grade:");
		grade = sc.nextDouble();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement prepstate = connection.prepareStatement(INSERT_STUDENT_SQL);
			prepstate.setInt(1, studID);
			prepstate.setString(2, course);
			prepstate.setDouble(3, grade);

			try (ResultSet result = prepstate.executeQuery()) {
				if (result.next()) {
					// int id = result.getInt(1);
					System.out.println("Inserted!");
					System.out.println("student_id | course_name | grade");
					System.out.println(studID + " | " + course + " | " + grade);
					connection.close();
				}
			} catch (Exception e) {
				System.err.println("ERROR: "+e.getMessage());
			}
		} catch (Exception e) {
			System.err.println("ERROR: "+e.getMessage());
		}
		
	}

	public static void showCourse() throws SQLException {
		String SELECT_COURSE_SQL = "SELECT c.student_id, c.course_name , c.grade, s.name FROM courses c JOIN students s ON c.student_id = s.id";
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(SELECT_COURSE_SQL);
			ResultSet result = ps.executeQuery();

			ResultSetMetaData meta = result.getMetaData();
			int columnCount = meta.getColumnCount();
			System.out.println("ID | Course Name | Grade | Student");
			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(result.getObject(i) + " | ");
				}
				connection.close();
				System.out.println();
			}
			
		} catch (Exception e) {
			System.err.println("ERROR: "+e.getMessage());
		}
		
	}

}
