package m5.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import m5.project.LibraryApplication;


public class DBConnection {

	private static final String URL = "jdbc:postgresql://localhost:5432/library_management";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static boolean verifylogin = false;
	
	private static Logger logger = LoggerFactory.getLogger(LibraryApplication.class);
	
	Connection conn = null;
	
	public void connect() {
	try {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Connected to PostgreSQL successfully.");
		
		verifylogin = true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		logger.error("Connection failed.", e);
		}
	}
    public static String getUrl() {
    	return URL; 
    	}
    public static String getUser() {
    	return USER; 
    	}
    public static String getPassword() {
    	return PASSWORD;
    	}
    public static boolean getVerifyLogin() {
    	return verifylogin;
    }
}

