CREATE TABLE IF NOT EXISTS transactions (
    id UUID NOT NULL,
    account_id VARCHAR NOT NULL,
    amount decimal NOT NULL,
    created_at date NOT NULL
);
