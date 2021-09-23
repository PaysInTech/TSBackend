CREATE TABLE samples
(
    id        BIGSERIAL PRIMARY KEY,
    some_text VARCHAR
);

INSERT INTO samples(some_text)
VALUES ('Hello World');