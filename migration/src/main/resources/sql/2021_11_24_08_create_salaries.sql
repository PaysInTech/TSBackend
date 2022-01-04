CREATE TABLE salaries
(
    id               BIGSERIAL PRIMARY KEY,
    job_detail_id    BIGINT,
    coins            INT         NOT NULL,
    currency         VARCHAR(5)  NOT NULL,
    user_id          VARCHAR     REFERENCES users (id) ON DELETE SET NULL,
    metadata         JSONB,
    metadata_version INT,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT salaries_job_details_fk
        FOREIGN KEY (job_detail_id)
            REFERENCES job_details (id)
);
