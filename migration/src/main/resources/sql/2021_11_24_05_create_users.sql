CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR     NOT NULL,
    email      VARCHAR     NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
)