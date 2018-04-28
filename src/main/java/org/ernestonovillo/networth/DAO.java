package org.ernestonovillo.networth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

/**
 * Data Access Object for resources to use.
 */
public class DAO implements InitializingBean {
    private final String dbPath;
    private Connection connection;

    // @Autowired
    public DAO(final String dbPath) {
        this.dbPath = dbPath;
        this.connection = null;
    }

    public boolean connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
        }

        return isConnected();
    }

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

    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
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

    public void dumpUsers() throws SQLException {
        final String sql = "SELECT name FROM users";
        final Statement statement = connection.createStatement();
        final ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Name: " + rs.getString("name"));
        }
    }

    public void dumpAssets() throws SQLException {
        final String sql = "SELECT name, value FROM assets";
        final Statement statement = connection.createStatement();
        final ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Name: " + rs.getString("name") + ", Value: " + rs.getDouble("value"));
        }
    }
}
