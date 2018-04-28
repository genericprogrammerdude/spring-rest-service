package org.ernestonovillo.networth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Data Access Object for resources to use. This is a bean.
 */
public class DAO {

    private final String dbPath;
    private Connection connection;

    public DAO(final String dbPath) {
        this.dbPath = dbPath;
        this.connection = null;
    }

    @PostConstruct
    public void connect() {
        assert (connection == null);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @PreDestroy
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (final SQLException e) {
            System.err.println(e);
        }
    }

    public List<User> getUsers() {
        List<User> users = null;

        if (connection == null) {
            System.err.println("DAO not connected to database");
            return users;
        }

        try {
            final String sql = "SELECT id, name, language FROM users";
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(sql);
            users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(
                        new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getLong("language")));
            }
        } catch (final SQLException e) {
            users = null;
            e.printStackTrace();
        }

        return users;
    }
}
