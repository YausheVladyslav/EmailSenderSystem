CREATE TABLE cron_jobs
(
    cron_id    SERIAL,
    PRIMARY KEY (cron_id),
    expression VARCHAR      NOT NULL,
    created_on TIMESTAMP(0) NOT NULL
);