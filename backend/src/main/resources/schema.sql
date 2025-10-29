CREATE TABLE IF NOT EXISTS interest_rates (
  rate_id BIGINT PRIMARY KEY,
  annual_rate DOUBLE
);

CREATE TABLE IF NOT EXISTS deposits (
  deposit_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  client_id BIGINT,
  principal_amount DECIMAL(19,4),
  accumulated_interest DECIMAL(19,4),
  start_date DATE,
  maturity_date DATE,
  status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS interest_transactions (
  transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  deposit_id BIGINT,
  interest_amount DECIMAL(19,4),
  transaction_date DATE,
  FOREIGN KEY (deposit_id) REFERENCES deposits(deposit_id)
);
