package org.ernestonovillo.networth;

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
    private long languageId;

    User(long id, String name, long language) {
        this.id = id;
        this.name = name;
        this.languageId = language;
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

    long getLanguage() {
        return languageId;
    }

    void setLanguage(long languageId) {
        this.languageId = languageId;
    }
}
