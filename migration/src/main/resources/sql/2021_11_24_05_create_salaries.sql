CREATE TABLE salaries
(
    id               BIGSERIAL PRIMARY KEY,
    job_detail_id    BIGINT,
    coins            INT NOT NULL,
    metadata         JSONB,
    metadata_version INT,
    created_at       TIMESTAMPTZ DEFAULT NOW(),

--  TODO: Add column `user_id` once authentication is available

    CONSTRAINT salaries_job_details_fk
        FOREIGN KEY (job_detail_id)
            REFERENCES job_details (id)
);
