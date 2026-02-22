-- auth test data for AuthIntegrationTest

INSERT INTO users (id, username, email, password, is_admin, created_at)
VALUES (1, 'admin', 'admin@email.com', '$2a$10$QplshJfwjX8R2fqMlH7z1.BYsXFYNdj0cXr9jBKcdZSSsSNJO5TCu', true,
        '2026-02-12 05:05:38.221');