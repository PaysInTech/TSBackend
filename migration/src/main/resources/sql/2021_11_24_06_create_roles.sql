CREATE TABLE roles
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(30) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Seed default roles
INSERT INTO roles(name)
VALUES ('Member'),
       ('Admin');