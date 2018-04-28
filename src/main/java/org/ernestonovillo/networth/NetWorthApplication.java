package org.ernestonovillo.networth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Handles application things.
 */
@SpringBootApplication
public class NetWorthApplication {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:data/networth.db");

			dumpUsers(connection);
			dumpAssets(connection);
		} catch (final SQLException e) {
			System.err.println(e.getMessage());
		}

		// SpringApplication.run(NetWorthApplication.class, args);

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (final SQLException e) {
			System.err.println(e);
		}
	}

	public static void dumpUsers(final Connection connection) throws SQLException {
		final String sql = "SELECT name FROM users";
		final Statement statement = connection.createStatement();
		final ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			System.out.println("Name: " + rs.getString("name"));
		}
	}

	public static void dumpAssets(final Connection connection) throws SQLException {
		final String sql = "SELECT name, value FROM assets";
		final Statement statement = connection.createStatement();
		final ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			System.out.println("Name: " + rs.getString("name") + ", Value: " + rs.getDouble("value"));
		}
	}
}
