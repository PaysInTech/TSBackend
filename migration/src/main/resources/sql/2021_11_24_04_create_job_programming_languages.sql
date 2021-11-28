CREATE TABLE job_programming_languages
(
    id                      BIGSERIAL PRIMARY KEY,
    job_detail_id           BIGINT NOT NULL,
    programming_language_id BIGINT NOT NULL,
    created_at              TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT company_technologies_job_details_fk
        FOREIGN KEY (job_detail_id)
            REFERENCES job_details (id),

    CONSTRAINT company_programming_languages_programming_languages_fk
        FOREIGN KEY (programming_language_id)
            REFERENCES programming_languages (id),

    CONSTRAINT job_programming_languages_uk UNIQUE (job_detail_id, programming_language_id)
)