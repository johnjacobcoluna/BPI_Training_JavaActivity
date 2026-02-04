package m5.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import m5.project.model.User;
import m5.util.DBConnection;

public class UserDAO extends DBConnection {
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	

	/*public static void userLogin(String user_name) {

		String addUser = "INSERT INTO USERS (user_name) VALUES (?)";
		String checkUser = "SELECT user_name FROM users where user_name = ?";
		//String validateToBorrow = "SELECT user_name FROM users where user_name = ?";
		try {
			Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(),DBConnection.getPassword());
			PreparedStatement ps = conn.prepareStatement(addUser);
			ps.setString(1, user_name);
			ps.executeUpdate();

			logger.info("\nLogin user:" + user_name);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.info("Invalid name."+e);
			System.exit(1);
		}

	}*/
	public static boolean userLogin(String user_name) {
	    String checkUser = "SELECT 1 FROM users WHERE user_name = ?";
	    String addUser = "INSERT INTO users (user_name) VALUES (?)";

	    try (Connection conn = DriverManager.getConnection(DBConnection.getUrl(), DBConnection.getUser(), DBConnection.getPassword())) {

	        // Check existence
	        try (PreparedStatement check = conn.prepareStatement(checkUser)) {
	            check.setString(1, user_name);
	            try (ResultSet rs = check.executeQuery()) {
	                if (rs.next()) {
	                    logger.info("Login user: " + user_name);
	                    return false;
	                }
	            }
	        }

	        // Insert if not exists
	        try (PreparedStatement insert = conn.prepareStatement(addUser)) {
	            insert.setString(1, user_name);
	            insert.executeUpdate();
	            logger.info("Login user: " + user_name);
	            return true;
	        }

	    } catch (SQLException e) {
	        logger.error("Invalid name. " + e.getMessage(), e);
	        return false;
	    }
	}
}
