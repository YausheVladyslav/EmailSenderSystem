CREATE TABLE users
(
    user_id    BIGSERIAL,
    PRIMARY KEY (user_id),
    username   VARCHAR(32)  NOT NULL,
    email      VARCHAR(64)  NOT NULL,
    created_on TIMESTAMP(0) NOT NULL
);

CREATE TABLE cron_jobs
(
    cron_id    SERIAL,
    PRIMARY KEY (cron_id),
    expression VARCHAR      NOT NULL,
    created_on TIMESTAMP(0) NOT NULL
);

CREATE TABLE log
(
    log_id     SERIAL,
    PRIMARY KEY (log_id),
    user_id    BIGINT REFERENCES users,
    type       VARCHAR      NOT NULL,
    created_on TIMESTAMP(0) NOT NULL
);