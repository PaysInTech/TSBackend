CREATE TABLE user_roles
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    VARCHAR     NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id    INT         NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);