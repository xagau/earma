CREATE TABLE accounts (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         balance DOUBLE PRECISION NOT NULL
);

CREATE TABLE deposits (
                         id SERIAL PRIMARY KEY,
                         account_id VARCHAR(16) NOT NULL,
                         amount DOUBLE PRECISION NOT NULL,
                         date DATE NOT NULL
);

CREATE TABLE withdrawals (
                            id SERIAL PRIMARY KEY,
                            account_id VARCHAR(16) NOT NULL,
                            amount DOUBLE PRECISION NOT NULL,
                            date DATE NOT NULL
);
