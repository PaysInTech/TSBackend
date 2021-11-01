CREATE TABLE contribution_levels
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    enabled    BOOLEAN     DEFAULT TRUE
);

-- Seed levels
INSERT INTO contribution_levels(name)
VALUES ('Individual Contributor'),
       ('Team Lead'),
       ('Manager');