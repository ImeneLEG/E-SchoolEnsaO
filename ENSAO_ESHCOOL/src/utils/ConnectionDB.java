package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static final String URL= "jdbc:mysql://localhost:3306/javafx";
	private static final String USERNAME="root";
	private static final String PASSWORD="";

	public static Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

}