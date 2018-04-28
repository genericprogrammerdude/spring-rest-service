package org.ernestonovillo.networth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.ernestonovillo.networth.models.NetWorthData;
import org.ernestonovillo.networth.models.User;

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
        assert (connection == null) : "DAO is already connected to database";
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
        assert (connection != null) : "DAO is not connected to database";

        List<User> users = null;

        try {
            final String sql = "SELECT users.id, users.name, languages.name AS langName FROM users INNER JOIN languages ON users.language = languages.id";
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(sql);
            users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("langName")));
            }
        } catch (final SQLException e) {
            users = null;
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(long id) {
        assert (connection != null) : "DAO is not connected to database";

        User user = null;

        try {
            final String sql = "SELECT users.id, users.name, languages.name AS langName FROM users INNER JOIN languages ON users.language = languages.id WHERE users.id = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(id, resultSet.getString("name"), resultSet.getString("langName"));

                if (resultSet.next()) {
                    System.err.println("Bad DB schema if select of a single index id returns more than one record");
                    user = null;
                }
            }
        } catch (final SQLException e) {
            user = null;
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Queries db and builds a NetWorthData instance with a user's assets and liabilities.
     *
     * @param id
     *            User id.
     * @return An initialized NetWorthData instance or null if the user id was not found.
     */
    public NetWorthData getNetWorthData(long id) {
        return null;
    }
}
