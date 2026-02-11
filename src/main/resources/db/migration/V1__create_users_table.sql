-- src/main/resources/db/migration/V1__create_users_table.sql

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(60) NOT NULL,
    is_admin   BOOLEAN     NOT NULL DEFAULT FALSE,
    email      VARCHAR(255),
    created_at timestamp   NOT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);
