package org.ernestonovillo.networth.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Model for a user of the Net Worth system.
 *
 * TODO: This should be annotated with @Entity to take advantage of the Java Persistence API (JPA).
 * See https://spring.io/guides/gs/accessing-data-jpa/ and
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY) // Allows Jackson to automatically serialize private fields
public class User {

    private final long id;
    private String name;
    private String language;

    public User(long id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    String getLanguage() {
        return language;
    }

    void setLanguage(final String language) {
        this.language = language;
    }
}
