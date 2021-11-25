CREATE TABLE job_details
(
    id                    BIGSERIAL PRIMARY KEY,
    company_name          VARCHAR NOT NULL,
    designation           VARCHAR NOT NULL,
    profile_id            BIGINT  NOT NULL,
    contribution_level_id BIGSERIAL,
    level                 VARCHAR(3),
    team                  VARCHAR,
    annual_ctc            DECIMAL NOT NULL,
    in_hand_per_month     DECIMAL NOT NULL,
    bonus                 DECIMAL,
    joining_date          DATE    NOT NULL,
    last_working_date     DATE,
    is_promoted           BOOLEAN,
    work_type             VARCHAR,
    location_state        VARCHAR,
    location_city         VARCHAR,
    created_at            TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT job_details_contribution_levels_fk
        FOREIGN KEY (contribution_level_id)
            REFERENCES contribution_levels (id),

    CONSTRAINT job_details_job_profiles_fk
        FOREIGN KEY (profile_id)
            REFERENCES job_profiles (id)
);