CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(12),
    sign_up_code VARCHAR(6)
);