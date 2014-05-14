package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static DBConnector instance;
	private Connection conn;

	private String database;
	private String username;
	private String password;
	private String host;

	private DBConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			host = "localhost";
			database = "";
			username = "";
			password = "";
			conn = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ database, username, password);
		} catch (Exception e) {
			System.err.println("Connection couldn't be established!");
		}
	}

	public static DBConnector sharedConnector() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

	public Connection getConnection() {
		return this.conn;
	}

	public String getDatabase() {
		return database;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}
}
