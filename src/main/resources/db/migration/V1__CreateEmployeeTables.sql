CREATE TABLE EMPLOYEE (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    ethnicity VARCHAR(20) NOT NULL,
    us_citizen BOOLEAN NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    line1 VARCHAR(255),
    line2 VARCHAR(255),
    city VARCHAR(255),
    state_code VARCHAR(2),
    zip_code VARCHAR(15)
);
