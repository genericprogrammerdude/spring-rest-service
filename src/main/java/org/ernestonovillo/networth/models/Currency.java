package org.ernestonovillo.networth.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Model to represent currencies.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY) // Allows Jackson to automatically serialize private fields
public class Currency {

    @SuppressWarnings("unused")
    private final long id;

    @SuppressWarnings("unused")
    private final String name;

    @SuppressWarnings("unused")
    private final String symbol;

    public Currency(long id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }
}
