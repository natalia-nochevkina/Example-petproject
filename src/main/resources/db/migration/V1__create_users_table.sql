-- src/main/resources/db/migration/V1__create_users_table.sql

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);