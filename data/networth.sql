--
-- File generated with SQLiteStudio v3.1.1 on Sat Apr 28 00:13:40 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: asset_categories
CREATE TABLE asset_categories (
    id   INTEGER NOT NULL
                 PRIMARY KEY AUTOINCREMENT
                 UNIQUE,
    name TEXT    NOT NULL
                 UNIQUE
);


-- Table: assets
CREATE TABLE assets (
    id       INTEGER NOT NULL
                     PRIMARY KEY AUTOINCREMENT
                     UNIQUE,
    name     TEXT    NOT NULL,
    value    REAL    NOT NULL
                     DEFAULT 0,
    currency INTEGER NOT NULL,
    category INTEGER NOT NULL,
    user     INTEGER NOT NULL,
    FOREIGN KEY (
        category
    )
    REFERENCES asset_categories (id),
    FOREIGN KEY (
        user
    )
    REFERENCES users (id),
    FOREIGN KEY (
        currency
    )
    REFERENCES currencies (id) 
);


-- Table: currencies
CREATE TABLE currencies (
    id     INTEGER NOT NULL
                   PRIMARY KEY AUTOINCREMENT
                   UNIQUE,
    name   TEXT    UNIQUE,
    symbol TEXT    NOT NULL
                   DEFAULT '$'
);


-- Table: languages
CREATE TABLE languages (
    id   INTEGER NOT NULL
                 PRIMARY KEY AUTOINCREMENT
                 UNIQUE,
    name TEXT    NOT NULL
                 UNIQUE
);


-- Table: liabilities
CREATE TABLE liabilities (
    id       INTEGER NOT NULL
                     PRIMARY KEY AUTOINCREMENT
                     UNIQUE,
    name     TEXT    NOT NULL
                     UNIQUE,
    value    REAL    NOT NULL
                     DEFAULT 0,
    currency INTEGER NOT NULL,
    category INTEGER NOT NULL,
    user     INTEGER NOT NULL,
    FOREIGN KEY (
        user
    )
    REFERENCES users (id),
    FOREIGN KEY (
        category
    )
    REFERENCES liability_categories (id),
    FOREIGN KEY (
        currency
    )
    REFERENCES currencies (id) 
);


-- Table: liability_categories
CREATE TABLE liability_categories (
    id   INTEGER NOT NULL
                 PRIMARY KEY AUTOINCREMENT
                 UNIQUE,
    name TEXT    NOT NULL
                 UNIQUE
);


-- Table: users
CREATE TABLE users (
    id       INTEGER NOT NULL
                     PRIMARY KEY AUTOINCREMENT
                     UNIQUE,
    name     TEXT    NOT NULL,
    language INTEGER NOT NULL
                     DEFAULT 1,
    FOREIGN KEY (
        language
    )
    REFERENCES languages (id) 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
