CREATE TABLE programming_languages
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    enabled    BOOLEAN     DEFAULT TRUE
);

-- Seed languages
INSERT INTO programming_languages(name)
VALUES ('Kotlin'),
       ('Java'),
       ('Scala'),
       ('Python'),
       ('Ruby'),
       ('Rust'),
       ('Groovy'),
       ('.NET'),
       ('C++'),
       ('C');