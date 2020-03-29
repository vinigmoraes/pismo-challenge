CREATE TABLE IF NOT EXISTS accounts (
    id UUID NOT NULL,
    document_number VARCHAR NOT NULL,
    available_credit_limit DECIMAL NOT NULL
);
