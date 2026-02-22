-- user test data for UserCRUDTest

INSERT INTO users (id, username, is_admin, email, password, created_at)
VALUES (20, 'admin', true, 'admin@email.com', '$2a$10$QplshJfwjX8R2fqMlH7z1.BYsXFYNdj0cXr9jBKcdZSSsSNJO5TCu',
        '2026-02-12 05:05:38.221'),
       (21, 'non-admin1', false, 'non-admin1@email.com', '$2a$10$QplshJfwjX8R2fqMlH7z1.BYsXFYNdj0cXr9jBKcdZSSsSNJO5TCu',
        '2026-02-12 05:05:38.221'),
       (22, 'non-admin2', false, 'non-admin2@email.com', '$2a$10$QplshJfwjX8R2fqMlH7z1.BYsXFYNdj0cXr9jBKcdZSSsSNJO5TCu',
        '2026-02-12 05:05:38.221'),
       (23, 'non-admin3', false, 'non-admin3@email.com', '$2a$10$QplshJfwjX8R2fqMlH7z1.BYsXFYNdj0cXr9jBKcdZSSsSNJO5TCu',
        '2026-02-12 05:05:38.221');


