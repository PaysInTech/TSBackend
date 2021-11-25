CREATE TABLE job_benefits
(
    id            BIGSERIAL PRIMARY KEY,
    job_detail_id BIGINT  NOT NULL,
    type          VARCHAR NOT NULL,
    amount        DECIMAL NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT job_benefits_job_details_fk
        FOREIGN KEY (job_detail_id)
            REFERENCES job_details (id)
)