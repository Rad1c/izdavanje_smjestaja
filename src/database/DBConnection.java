package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import parsers.XMLParser;

public class DBConnection {
	static Connection connection = null;
	static ConnectionInfo connInfo = XMLParser.getConnectionString();
	
	public static Connection getConnection() {
		String connString = "jdbc:jtds:sqlserver://";
		connString += connInfo.getAddress() + ":" + connInfo.getPort() + "/" + connInfo.getDatabase();
		
		try {
			if(connection == null) {
				connection = DriverManager.getConnection(connString, connInfo.getUsername(), connInfo.getPassword());
			}
		} catch (SQLException e) {
			System.out.println("Error with connecting to database!");
			e.printStackTrace();
		}
		return connection;
	}
	
}
