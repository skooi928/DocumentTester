INSERT INTO interest_rates (rate_id, annual_rate)
VALUES (1, 0.035);

INSERT INTO deposits (deposit_id, client_id, principal_amount, accumulated_interest, start_date, maturity_date, status)
VALUES 
    (1, 1001, 10000.00, 0.00, CURRENT_DATE, DATEADD('DAY', 30, CURRENT_DATE), 'ACTIVE'),
    (2, 1002, 5000.00, 0.00, CURRENT_DATE, DATEADD('DAY', 10, CURRENT_DATE), 'ACTIVE'),
    (3, 1003, 20000.00, 0.00, CURRENT_DATE, CURRENT_DATE, 'ACTIVE');
