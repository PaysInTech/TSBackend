CREATE TABLE job_technologies
(
    id            BIGSERIAL PRIMARY KEY,
    job_detail_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT company_technologies_job_details_fk
        FOREIGN KEY (job_detail_id)
            REFERENCES job_details (id),

    CONSTRAINT company_technologies_technologies_fk
        FOREIGN KEY (technology_id)
            REFERENCES technologies (id)
);

