CREATE TABLE users
(
    id         VARCHAR PRIMARY KEY,
    username   VARCHAR     NOT NULL,
    email      VARCHAR     NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS users_username_idx
    ON users (username);

CREATE INDEX IF NOT EXISTS users_email_idx
    ON users (email);