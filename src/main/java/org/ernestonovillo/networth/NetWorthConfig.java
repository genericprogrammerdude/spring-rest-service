package org.ernestonovillo.networth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Configuration for beans.
 */
@Configuration
public class NetWorthConfig {

    @Bean
    @Scope("singleton")
    public DAO dao(@Value("${networth.dbpath}") String dbPath) {
        return new DAO(dbPath);
    }

    @Bean
    @Scope("prototype")
    public NetWorthController netWorthController() {
        // That null gets replaced with the value of ${networth.dbpath} in dao()
        return new NetWorthController(dao(null));
    }
}
