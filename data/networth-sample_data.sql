--
-- File generated with SQLiteStudio v3.1.1 on Sat Apr 28 00:55:05 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: asset_categories
CREATE TABLE `asset_categories` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL UNIQUE
);
INSERT INTO asset_categories (id, name) VALUES (1, 'Cash and Investments');
INSERT INTO asset_categories (id, name) VALUES (2, 'Long Term Assets');
INSERT INTO asset_categories (id, name) VALUES (3, 'Other');

-- Table: assets
CREATE TABLE `assets` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL,
	`value`	REAL NOT NULL DEFAULT 0,
	`currency`	INTEGER NOT NULL,
	`category`	INTEGER NOT NULL,
	`user`	INTEGER NOT NULL,
	FOREIGN KEY(`category`) REFERENCES `asset_categories`(`id`),
	FOREIGN KEY(`user`) REFERENCES `users`(`id`),
	FOREIGN KEY(`currency`) REFERENCES `currencies`(`id`)
);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (1, 'Chequing', 2000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (2, 'Savings for Taxes', 2000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (3, 'Rainy Day Fund', 506, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (4, 'Savings for Fun', 5000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (5, 'Savings for Travel', 400, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (6, 'Savings for Personal Development', 200, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (7, 'Investment 1', 5000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (8, 'Investment 2', 60000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (9, 'Investment 3', 30000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (10, 'Investment 4', 50000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (11, 'Investment 5', 24000, 1, 1, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (12, 'Primary Home', 455000, 1, 2, 2);
INSERT INTO assets (id, name, value, currency, category, user) VALUES (13, 'Second Home', 1564321, 1, 2, 2);

-- Table: currencies
CREATE TABLE `currencies` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT UNIQUE,
	`symbol`	TEXT NOT NULL DEFAULT '$'
);
INSERT INTO currencies (id, name, symbol) VALUES (1, 'Canadian Dollar', 'CAD$');
INSERT INTO currencies (id, name, symbol) VALUES (2, 'US Dollar', 'US$');

-- Table: languages
CREATE TABLE "languages" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL UNIQUE
);
INSERT INTO languages (id, name) VALUES (1, 'English');
INSERT INTO languages (id, name) VALUES (2, 'French');

-- Table: liabilities
CREATE TABLE "liabilities" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL UNIQUE,
	`value`	REAL NOT NULL DEFAULT 0,
	`currency`	INTEGER NOT NULL,
	`category`	INTEGER NOT NULL,
	`user`	INTEGER NOT NULL,
	FOREIGN KEY(`user`) REFERENCES `users`(`id`),
	FOREIGN KEY(`category`) REFERENCES `liability_categories`(`id`),
	FOREIGN KEY(`currency`) REFERENCES `currencies`(`id`)
);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (1, 'Credit Card 1', 4342, 1, 1, 2);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (2, 'Credit Card 2', 322, 1, 1, 2);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (3, 'Mortgage 1', 250999, 1, 2, 2);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (4, 'Mortgage 2', 632634, 1, 2, 2);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (5, 'Line of Credit', 10000, 1, 2, 2);
INSERT INTO liabilities (id, name, value, currency, category, user) VALUES (6, 'Investment Loan', 10000, 1, 2, 2);

-- Table: liability_categories
CREATE TABLE "liability_categories" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL UNIQUE
);
INSERT INTO liability_categories (id, name) VALUES (1, 'Short Term Liabilities');
INSERT INTO liability_categories (id, name) VALUES (2, 'Long Term Debt');

-- Table: users
CREATE TABLE "users" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL,
	`language`	INTEGER NOT NULL DEFAULT 1,
	FOREIGN KEY(`language`) REFERENCES `languages`(`id`)
);
INSERT INTO users (id, name, language) VALUES (1, 'Jacques Cousteau', 2);
INSERT INTO users (id, name, language) VALUES (2, 'John Steinbeck', 1);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
