package ro.danradu.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDatabase {
	public static final String SERVER = "localhost";
	public static final String DATABASE = "api-db";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public MysqlDatabase() {
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("exception");
		}
	}

	public void openConnection() {
		try {
			this.connection = (Connection) DriverManager
					.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE
							+ "?user=" + USERNAME + "&password=" + PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if (this.connection != null)
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public Statement getStatement() {
		Statement result = null;
		try {
			result = (Statement) this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
