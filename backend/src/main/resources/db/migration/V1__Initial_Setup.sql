CREATE TABLE department (
    id INT8 NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id INT8 NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department_id INT
);

CREATE SEQUENCE users_seq START 1 INCREMENT 1;
CREATE SEQUENCE department_seq START 1 INCREMENT 1;

ALTER TABLE users
    ADD CONSTRAINT users_department_fk
        FOREIGN KEY (department_id)
            REFERENCES department;