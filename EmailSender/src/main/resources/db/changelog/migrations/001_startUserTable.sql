CREATE TABLE users
(
    user_id    BIGSERIAL,
    PRIMARY KEY (user_id),
    username   VARCHAR(32) NOT NULL,
    email      VARCHAR(64) NOT NULL,
    created_on TIMESTAMP(0)   NOT NULL
);