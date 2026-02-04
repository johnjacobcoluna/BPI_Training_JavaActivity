package m5.project.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import m5.project.model.Book;
import m5.util.DBConnection;

public class BookDAO extends DBConnection{
	
	private static Book b = new Book();
	private static Logger logger = LoggerFactory.getLogger(BookDAO.class);
	
	public static void viewBook() {
		String countBookList = "SELECT 1 FROM books LIMIT 1";
		String viewBookList = "SELECT * FROM books";
		try {
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(countBookList);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
			
				System.out.println("ID | Title | Author | Availability");
			
				ps = conn.prepareStatement(viewBookList);
				rs = ps.executeQuery();
			
				while (rs.next()) {
					
					b.setBookId(rs.getInt("book_id"));
					b.setBookTitle(rs.getString("book_title"));
					b.setBookAuthor(rs.getString("book_author"));
					b.setIsAvailable(rs.getBoolean("is_available"));
					System.out.println( b.getBookId() + " | " + b.getBookTitle() + " | " + b.getBookAuthor() + " | " + b.getIsAvailable());
				}
			} else {
				throw new Exception("No Book Available.");
			}
			conn.close();
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public static void viewAvailBook() {
		String countAvailBookList = "SELECT 1 FROM books where is_available = true LIMIT 1";
		String viewAvailBookList = "SELECT * FROM books where is_available = true";
		try {
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(countAvailBookList);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
			
				System.out.println("ID | Title | Author | Availability");
			
				ps = conn.prepareStatement(viewAvailBookList);
				rs = ps.executeQuery();
			
				while (rs.next()) {
					
					b.setBookId(rs.getInt("book_id"));
					b.setBookTitle(rs.getString("book_title"));
					b.setBookAuthor(rs.getString("book_author"));
					b.setIsAvailable(rs.getBoolean("is_available"));
					System.out.println( b.getBookId() + " | " + b.getBookTitle() + " | " + b.getBookAuthor() + " | " + b.getIsAvailable());
				}
			} else {
				throw new Exception("No Book Available.");
			}
			conn.close();
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public static void addBook(Scanner input) {
		String booktitle, bookauthor;
		int bookid;
		
		String addBookRow = "INSERT INTO BOOKS (book_id, book_title, book_author, is_available) VALUES (?, ?, ?, ?)";
		try {
			System.out.print("Enter ID: ");
			bookid = input.nextInt();
			input.nextLine();
			
			System.out.print("Enter Title: ");
			booktitle = input.nextLine();
			
			System.out.print("Enter Author: ");
			bookauthor = input.nextLine();
			
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(addBookRow);
			ps.setInt(1, bookid);
			ps.setString(2, booktitle);
			ps.setString(3, bookauthor);
			ps.setBoolean(4, true);
		
			ps.executeUpdate();
		
			logger.info("Added a book.");
			
			conn.close();
			ps.close();
			
		} catch (SQLException e) {
			logger.info("Cannot add Duplicate ID.");
		}
	}
	
	public static void removeBook(Scanner input) {
		int bookid;
		
		String removeBookRow = "DELETE FROM books where book_id = ?";
		String removeLoanRow = "DELETE FROM loans where loan_bookid = ?";
		String validateToRemove = "SELECT * FROM books where book_id = ?";
		String countBorrowedBook = "SELECT 1 FROM loans LIMIT 1";
		String resetLoanID = "SELECT setval(pg_get_serial_sequence('loans', 'loan_id'), 1, false)";
		try {
			System.out.print("Enter ID: ");
			bookid = input.nextInt();
			input.nextLine();
			
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(validateToRemove);
			ps.setInt(1, bookid);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
			
				bookid = rs.getInt("book_id");
				
				ps = conn.prepareStatement(removeLoanRow);
				ps.setInt(1, bookid);
				
				ps.executeUpdate();
				
				ps = conn.prepareStatement(removeBookRow);
				ps.setInt(1, bookid);
				
				ps.executeUpdate();
				
				ps = conn.prepareStatement(countBorrowedBook);
				rs = ps.executeQuery();
				
				if (!rs.next()) {
					ps = conn.prepareStatement(resetLoanID);
					rs = ps.executeQuery();
				} 
				logger.info("Removed a book.");
			} else {
				throw new Exception("Cannot Remove a Non-existent Book.");
			}
			
			ps.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public static void updateBook(Scanner input) {
		String booktitle, bookauthor, newtitle = null, newauthor = null;
		int bookid;
		boolean isavailable;
		
		String updateBookRow = "UPDATE books SET book_title = ?, book_author = ? where book_id = ?";
		String viewAvailability = "SELECT * FROM books WHERE book_id = ?";
		try {
			System.out.print("Enter ID: ");
			bookid = input.nextInt();
			input.nextLine();
			
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(viewAvailability);
			ps.setInt(1, bookid);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				bookid = rs.getInt("book_id");
				booktitle = rs.getString("book_title");
				bookauthor = rs.getString("book_author");
				isavailable = rs.getBoolean("is_available");
				
				if (isavailable) {
					System.out.print("Enter Title: ");
					newtitle = input.nextLine();
					
					System.out.print("Enter Author: ");
					newauthor = input.nextLine();
					
					ps = conn.prepareStatement(updateBookRow);
					ps.setInt(3, bookid);
					
					if (newtitle.isBlank()) {
						ps.setString(1, booktitle);
					} else {
						ps.setString(1, newtitle);
					}
					
					if (newauthor.isBlank()) {
						ps.setString(2, bookauthor);
					} else {
						ps.setString(2, newauthor);
					}
					
					ps.executeUpdate();
					
					logger.info("Updated a book.");
				} else {
					throw new Exception("Selected book cannot be updated; currently borrowed.");
				}
				
			} else {
				throw new Exception("Cannot Update a Non-existent Book.");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
}
