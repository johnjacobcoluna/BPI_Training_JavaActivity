package m5.project;

import m5.util.DBConnection;

public class Main {
	
	public static void main(String[] args) throws Exception{
		LibraryApplication app = new LibraryApplication();
		DBConnection db = new DBConnection();
		
		db.connect();
		app.start();
	}

}
