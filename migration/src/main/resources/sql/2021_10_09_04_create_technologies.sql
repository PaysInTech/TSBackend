CREATE TABLE technologies
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    enabled    BOOLEAN     DEFAULT TRUE
);

-- Seed technologies
INSERT INTO technologies(name)
VALUES ('Android (Native)'),
       ('iOS (Native)'),
       ('Web'),
       ('Flutter'),
       ('React Native'),
       ('JVM Backend'),
       ('Java'),
       ('.NET'),
       ('Full Stack'),
       ('Rails');