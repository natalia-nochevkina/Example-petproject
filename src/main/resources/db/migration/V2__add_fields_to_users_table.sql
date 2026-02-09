-- src/main/resources/db/migration/V1__create_users_table.sql

ALTER TABLE users
    ADD password VARCHAR(60);

ALTER TABLE users
    ADD email VARCHAR(255);

ALTER TABLE users
    ADD created_at timestamp;
