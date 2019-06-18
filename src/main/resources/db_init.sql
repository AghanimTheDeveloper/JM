CREATE TABLE IF NOT EXISTS users
(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL ,
    login VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    role VARCHAR(255) NOT NULL ,
    CONSTRAINT users_pk
        PRIMARY KEY (id)
);

CREATE UNIQUE INDEX users_login_uindex
    ON users (login);

CREATE UNIQUE INDEX users_name_uindex
    ON users (name);

INSERT INTO users (id, name, login, password, role) VALUES (1, 'admin', 'admin', 'admin', 'admin');