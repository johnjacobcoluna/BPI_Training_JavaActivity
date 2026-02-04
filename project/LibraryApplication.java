package m5.project;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import m5.project.dao.BookDAO;
import m5.project.dao.UserDAO;
import m5.project.model.User;
import m5.project.dao.LoanDAO;
import m5.util.DBConnection;

public class LibraryApplication extends DBConnection{ 
	Scanner sc=new Scanner(System.in);
	private User user;
	Integer option;
	boolean running = true;
	private static Logger logger = LoggerFactory.getLogger(LibraryApplication.class);
	
	public void start() {
		System.out.println("Name: ");
		String name= sc.nextLine();
		this.user=new User(name);
		UserDAO.userLogin(name);
		
		Scanner input = new Scanner(System.in);
		if (DBConnection.getVerifyLogin()) {
			while (running) {
			
				try {
					System.out.println("===================================");
					System.out.println("==      LIBRARY MANAGEMENT       ==");
					System.out.println("===================================");
					System.out.println("[1] DISPLAY ALL BOOKS");
					System.out.println("[2] DISPLAY AVAILABLE BOOKS");
					System.out.println("[3] DISPLAY ALL BORROWED BOOKS");
					System.out.println("[4] BORROW BOOK");
					System.out.println("[5] RETURN BOOK");
					System.out.println("[6] ADD BOOK");
					System.out.println("[7] REMOVE BOOK");
					System.out.println("[8] UPDATE BOOK");
					System.out.println("[0] EXIT");
					System.out.print("ENTER OPTION: ");
					option = input.nextInt();
					input.nextLine();
				
						switch (option) {
						case 1:
							BookDAO.viewBook();
							break;
						case 2:
							BookDAO.viewAvailBook();
							break;
						case 3:
							LoanDAO.viewBorrowedBook();
							break;
						case 4:
							BookDAO.viewAvailBook();
							LoanDAO.borrowBook(input, user);
							break;
						case 5:
							LoanDAO.viewBorrowedBook();
							LoanDAO.returnBook(input);
							break;
						case 6:
							BookDAO.addBook(input);
							break;
						case 7:
							BookDAO.viewBook();
							BookDAO.removeBook(input);
							break;
						case 8:
							BookDAO.viewBook();
							BookDAO.updateBook(input);
							break;
						case 0:
							running = false;
							logger.info("Program terminated.");
							System.exit(0);
							break;
						default:
							throw new Exception("Invalid option. Please try again.\n");
					}
				} catch (InputMismatchException e) {
					logger.info("Invalid input; should be numeric.");
					input.nextLine();
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
		System.exit(0);
	}
	
}
