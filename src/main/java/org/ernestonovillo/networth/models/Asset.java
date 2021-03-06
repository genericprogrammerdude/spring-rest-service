package org.ernestonovillo.networth.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;;

/**
 * Model to represent liabilities.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY) // Allows Jackson to automatically serialize private fields
public class Asset {

    final long id;
    final String name;
    final double value;
    final Currency currency;
    final AssetCategory category;
    final User user;

    public Asset(long id, String name, double value, Currency currency, AssetCategory category, User user) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.currency = currency;
        this.category = category;
        this.user = user;
    }
}
