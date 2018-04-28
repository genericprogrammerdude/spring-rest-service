package org.ernestonovillo.networth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
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
		} catch (final SQLException e) {
			System.err.println(e.getMessage());
		}

		SpringApplication.run(NetWorthApplication.class, args);

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (final SQLException e) {
			System.err.println(e);
		}
	}
}
