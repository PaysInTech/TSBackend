CREATE TABLE job_profiles
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    enabled    BOOLEAN     DEFAULT TRUE
);

-- Seed profiles
INSERT INTO job_profiles(name)
VALUES ('Software Engineer'),
       ('Product Manager'),
       ('QA Engineer'),
       ('UI/UX Designer'),
       ('Engineering Manager'),
       ('Director'),
       ('VP'),
       ('CXO');