package org.ernestonovillo.networth.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Model to represent liability categories.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY) // Allows Jackson to automatically serialize private fields
public class AssetCategory {

    @SuppressWarnings("unused")
    private final long id;

    @SuppressWarnings("unused")
    private final String name;

    public AssetCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
