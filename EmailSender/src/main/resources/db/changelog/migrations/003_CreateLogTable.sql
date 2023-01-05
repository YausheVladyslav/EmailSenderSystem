CREATE TABLE log
(
    log_id     SERIAL,
    PRIMARY KEY (log_id),
    user_id    BIGINT REFERENCES users,
    type       VARCHAR      NOT NULL,
    created_on TIMESTAMP(0) NOT NULL
);