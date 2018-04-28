package org.ernestonovillo.networth.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Model that contains all relevant information to compute a user's net worth.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY) // Allows Jackson to automatically serialize private fields
public class NetWorthData {

    @SuppressWarnings("unused")
    private final User user;

    @SuppressWarnings("unused")
    private final List<Asset> assets;
    
    @SuppressWarnings("unused")
    private final List<Liability> liabilities;
    
    NetWorthData(User user, List<Asset> assets, List<Liability> liabilities) {
        this.user = user;
        this.assets = assets;
        this.liabilities = liabilities;
    }
}
