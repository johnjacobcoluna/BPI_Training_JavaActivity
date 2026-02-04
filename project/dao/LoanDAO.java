package m5.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import m5.project.model.Loan;
import m5.project.model.User;
import m5.util.DBConnection;

public class LoanDAO extends DBConnection {

	private static Loan l = new Loan();
	private static Logger logger = LoggerFactory.getLogger(LoanDAO.class);

	public static void viewBorrowedBook() {
		String countBorrowedBookList = "SELECT 1 FROM loans LIMIT 1";
		String viewBorrowedBookList = "SELECT loan_id, loan_booktitle, borrower_name, loan_bookid FROM loans";
		try {
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(),
					DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(countBorrowedBookList);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("Loan ID | Title | Borrower_Name | Book ID");

				ps = conn.prepareStatement(viewBorrowedBookList);
				rs = ps.executeQuery();

				while (rs.next()) {

					l.setLoanId(rs.getInt("loan_id"));
					l.setLoanBookTitle(rs.getString("loan_booktitle"));
					l.setBorrowerName(rs.getString("borrower_name"));
					l.setLoanBookId(rs.getInt("loan_bookid"));
					System.out.println(l.getLoanId() + " | " + l.getLoanBookTitle() + " | " + l.getBorrowerName() + " | "
							+ l.getLoanBookId());
				}
			} else {
				throw new Exception("No Borrowed Book.");
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

	/*public static void borrowBook(Scanner input, User user) {
		int bookid;
		String booktitle;
		String borrower_name = user.getName();

		String addLoanRow = "INSERT INTO loans (loan_booktitle, borrower_name, loan_bookid) VALUES (?, ?, ?)";
		String updateBookAvail = "UPDATE books SET is_available = ? where book_id = ?";
		String validateToBorrow = "SELECT book_title, book_author, is_available from books where book_id = ?";
		String validateUser = "SELECT 1 from users where user_name = ?";
		try {
			System.out.print("Enter ID: ");
			bookid = input.nextInt();
			input.nextLine();
			System.out.println(borrower_name);
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(),
					DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(validateToBorrow);
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement ps2 = conn.prepareStatement(validateUser);
			ps2.setString(1, borrower_name);
			ResultSet rs2 = ps.executeQuery();
			
			if (rs2.next()) {

				booktitle = rs.getString("book_title");
				borrower_name = rs2.getString("borrower_name");
				bookid = rs.getInt("book_id");

				ps = conn.prepareStatement(updateBookAvail);
				ps.setBoolean(1, false);
				ps.setInt(2, bookid);

				ps.executeUpdate();
				ps = conn.prepareStatement(addLoanRow);
				ps.setString(1, booktitle);
				ps.setString(2, borrower_name);
				ps.setInt(3, bookid);

				ps.executeUpdate();

				logger.info("Borrowed a book.");
			} else {
				throw new Exception("Cannot Remove a Non-existent Book.");
			}
			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}*/
	public static void borrowBook(Scanner input, User user) {
	    System.out.print("Enter ID: ");
	    int bookId = input.nextInt();
	    input.nextLine();

	    String borrowerName = user.getName();

	    String checkUser   = "SELECT 1 FROM users WHERE user_name = ?";
	    String selectBook  = "SELECT book_title, is_available FROM books WHERE book_id = ?";
	    String updateAvail = "UPDATE books SET is_available = FALSE WHERE book_id = ? AND is_available = TRUE";
	    String insertLoan  = "INSERT INTO loans (loan_booktitle, borrower_name, loan_bookid) VALUES (?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword())) {
	        conn.setAutoCommit(false); // begin TX

	        // 1) Validate user
	        try (PreparedStatement psUser = conn.prepareStatement(checkUser)) {
	            psUser.setString(1, borrowerName);
	            try (ResultSet rs = psUser.executeQuery()) {
	                if (!rs.next()) {
	                    throw new Exception("User does not exist: " + borrowerName);
	                }
	            }
	        }

	        // 2) Read book
	        String bookTitle;
	        boolean isAvailable;
	        try (PreparedStatement psSel = conn.prepareStatement(selectBook)) {
	            psSel.setInt(1, bookId);
	            try (ResultSet rs = psSel.executeQuery()) {
	                if (!rs.next()) {
	                    throw new Exception("Book not found (ID: " + bookId + ").");
	                }
	                bookTitle = rs.getString("book_title");
	                isAvailable = rs.getBoolean("is_available");
	            }
	        }
	        if (!isAvailable) {
	            throw new Exception("Book is not available (ID: " + bookId + ").");
	        }

	        // 3) Atomically mark as unavailable (guard prevents races)
	        int affected;
	        try (PreparedStatement psUpd = conn.prepareStatement(updateAvail)) {
	            psUpd.setInt(1, bookId);
	            affected = psUpd.executeUpdate();
	        }
	        if (affected == 0) {
	            throw new Exception("Book just became unavailable (ID: " + bookId + "). Please try again.");
	        }

	        // 4) Insert loan
	        try (PreparedStatement psIns = conn.prepareStatement(insertLoan)) {
	            psIns.setString(1, bookTitle);
	            psIns.setString(2, borrowerName);
	            psIns.setInt(3, bookId);
	            psIns.executeUpdate();
	        }

	        conn.commit();
	        logger.info("Borrowed a book. Title: " + bookTitle + ", Borrower: " + borrowerName + ", Book ID: " + bookId);
	    } catch (Exception e) {
	        logger.info(e.getMessage());
	        // Optional: if you keep a reference to conn, rollback on error
	    }
	}

	public static void returnBook(Scanner input) {
		int loanid, bookid;
		String borrower_name;

		String removeLoanRow = "DELETE FROM loans where loan_id = ?";
		String validateLoanedBook = "SELECT loan_booktitle, borrower_name, loan_bookid FROM loans where loan_id = ?";
		String resetLoanID = "SELECT setval(pg_get_serial_sequence('loans', 'loan_id'), 1, false)";
		String returnBookRow = "UPDATE books SET is_available = ? where book_id = ?";
		String countBorrowedBook = "SELECT 1 FROM loans LIMIT 1";

		try {
			System.out.print("Enter Loan ID: ");
			loanid = input.nextInt();
			input.nextLine();

			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(),
					DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(validateLoanedBook);
			ps.setInt(1, loanid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				bookid = rs.getInt("loan_bookid");

				ps = conn.prepareStatement(returnBookRow);
				ps.setBoolean(1, true);
				ps.setInt(2, bookid);

				ps.executeUpdate();

				ps = conn.prepareStatement(removeLoanRow);
				ps.setInt(1, loanid);

				ps.executeUpdate();

				ps = conn.prepareStatement(countBorrowedBook);
				rs = ps.executeQuery();

				if (!rs.next()) {
					ps = conn.prepareStatement(resetLoanID);
					rs = ps.executeQuery();
				}
				logger.info("Returned a book.");

			} else {
				throw new Exception("Cannot Remove a Non-existent Book.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}
