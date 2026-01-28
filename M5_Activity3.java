package M5_Activity3;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Scanner;

public class M5_Activity3 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int option;
		Students stud = new Students();
		Courses course = new Courses();

		do {
			System.out.println("==== STUDENT COURSE MANAGEMENT =====");
			System.out.println("1 - Add Student");
			System.out.println("2 - Add Course");
			System.out.println("3 - Show Students");
			System.out.println("4 - Show Courses");
			System.out.println("0 - Exit");

			System.out.print("Enter Choice:");

			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
			case 1:
				stud.addStudent(sc);
				break;
			case 2:
				course.addCourse(sc);
				break;
			case 3:
				stud.showStudent();
				break;
			case 4:
				course.showCourse();
				break;
			case 0:
				System.out.println("Exiting application. Goodbye!");
				System.exit(0);
			default:
				System.out.println("Invalid option! Please try again.\n");
			}
		} while (option != 'D');
		sc.close();
	}

}
