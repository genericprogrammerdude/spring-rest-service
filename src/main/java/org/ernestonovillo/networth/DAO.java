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

import org.ernestonovillo.networth.models.Asset;
import org.ernestonovillo.networth.models.AssetCategory;
import org.ernestonovillo.networth.models.Currency;
import org.ernestonovillo.networth.models.Language;
import org.ernestonovillo.networth.models.Liability;
import org.ernestonovillo.networth.models.LiabilityCategory;
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
            final String sql = "SELECT users.id, users.name, languages.id AS langId, languages.name AS langName FROM users INNER JOIN languages ON users.language = languages.id";
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(sql);
            users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("name"),
                        new Language(resultSet.getLong("langId"), resultSet.getString("langName"))));
            }
        } catch (final SQLException e) {
            users = null;
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(long userId) {
        assert (connection != null) : "DAO is not connected to database";

        User user = null;

        try {
            final String sql = "SELECT users.id, users.name, languages.id AS langId, languages.name AS langName FROM "
                    + "users INNER JOIN languages ON users.language = languages.id WHERE users.id = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(userId, resultSet.getString("name"),
                        new Language(resultSet.getLong("id"), resultSet.getString("langName")));

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
     */
    public NetWorthData getNetWorthData(long userId) {
        assert (connection != null) : "DAO is not connected to database";

        NetWorthData netWorthData = null;

        final User user = getUser(userId);
        if (user == null) {
            return null;
        }

        final List<Asset> assets = getAssets(user);
        if (assets == null) {
            return null;
        }

        final List<Liability> liabilities = getLiabilities(user);
        if (liabilities == null) {
            return null;
        }

        netWorthData = new NetWorthData(user, assets, liabilities);

        return netWorthData;
    }

    private List<Asset> getAssets(User user) {
        assert (connection != null) : "DAO is not connected to database";

        List<Asset> assets = null;

        try {
            final String sql = "SELECT assets.id, assets.name, assets.value, assets.currency, currencies.id AS currId, "
                    + "currencies.name AS currName, currencies.symbol, asset_categories.id AS assId, "
                    + "asset_categories.name AS assName FROM assets "
                    + "JOIN currencies ON assets.currency = currencies.id "
                    + "JOIN asset_categories ON assets.category = asset_categories.id WHERE assets.user = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getId());
            final ResultSet resultSet = statement.executeQuery();
            assets = new ArrayList<Asset>();
            while (resultSet.next()) {
                // TODO: This is AWFUL! New Currency and AssetCategory created for each returned record. The ORM
                // aspect of this class should make better use of memory. Also, getAssets() and getLiabilities()
                // share some code that could be refactored.
                final Currency currency = new Currency(resultSet.getLong("currId"), resultSet.getString("currName"),
                        resultSet.getString("symbol"));
                final AssetCategory assCat = new AssetCategory(resultSet.getLong("assId"),
                        resultSet.getString("assName"));
                final Asset asset = new Asset(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getDouble("value"), currency, assCat, user);
                assets.add(asset);
            }
        } catch (final SQLException e) {
            assets = null;
            e.printStackTrace();
        }

        return assets;
    }

    private List<Liability> getLiabilities(User user) {
        assert (connection != null) : "DAO is not connected to database";

        List<Liability> liabilities = null;

        try {
            final String sql = "SELECT liabilities.id, liabilities.name, liabilities.value, liabilities.currency, "
                    + "currencies.id AS currId, currencies.name AS currName, currencies.symbol, asset_categories.id AS assId, "
                    + "asset_categories.name AS assName FROM liabilities "
                    + "JOIN currencies ON liabilities.currency = currencies.id "
                    + "JOIN asset_categories ON liabilities.category = asset_categories.id WHERE liabilities.user = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getId());
            final ResultSet resultSet = statement.executeQuery();
            liabilities = new ArrayList<Liability>();
            while (resultSet.next()) {
                // TODO: This is AWFUL! New Currency and LiabilityCategory created for each returned record. The ORM
                // aspect of this class should make better use of memory. Also, getAssets() and getLiabilities()
                // share some code that could be refactored.
                final Currency currency = new Currency(resultSet.getLong("currId"), resultSet.getString("currName"),
                        resultSet.getString("symbol"));
                final LiabilityCategory assCat = new LiabilityCategory(resultSet.getLong("assId"),
                        resultSet.getString("assName"));
                final Liability liability = new Liability(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getDouble("value"), currency, assCat, user);
                liabilities.add(liability);
            }
        } catch (final SQLException e) {
            liabilities = null;
            e.printStackTrace();
        }

        return liabilities;
    }
}
