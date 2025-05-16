-- Insert test users
INSERT INTO users (name, email, password, role, is_active, created_at)
VALUES ('Alice Smith', 'alice@example.com', 'admin123', 'ROLE_ADMIN', TRUE,
        '2024-01-15 09:00:00'),
       ('Bob Wilson', 'bob@example.com', 'user123', 'ROLE_USER', TRUE,
        '2024-02-01 14:30:00'),
       ('Charlie Brown', 'charlie@example.com', 'user123', 'ROLE_USER', FALSE,
        '2024-03-10 18:15:00');

-- Insert test short URLs
INSERT INTO short_urls (short_key, original_url, is_private, expires_at, created_by, click_count,
                        created_at)
VALUES ('aBc12D', 'https://www.example.com/very-long-path/article123', FALSE, '2025-01-01 00:00:00',
        1, 42, '2024-01-20 10:00:00'),
       ('XyZ34', 'https://company.com/special-offer', TRUE, NULL, 2, 15, '2024-02-05 11:30:00'),
       ('QwEr56', 'https://blog.site/post/learn-sql', FALSE, '2024-06-30 23:59:59', 1, 87,
        '2024-01-25 15:45:00'),
       ('7uJk89', 'https://internal.docs/system-guide', TRUE, '2024-12-31 00:00:00', 3, 3,
        '2024-03-12 09:15:00'),
       ('mNvB09', 'https://public.resources/download', FALSE, NULL, NULL, 102,
        '2024-02-15 16:20:00');