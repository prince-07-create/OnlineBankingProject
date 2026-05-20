-- Insert a test user with username 'testuser' and password 'password'
-- We use WHERE NOT EXISTS to avoid errors if the script runs multiple times
INSERT INTO users (username, email, phone, password, balance) 
SELECT 'testuser', 'test@test.com', '1234567890', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 1500.00
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'testuser');

-- Insert initial deposit transaction for the test user
INSERT INTO transactions (amount, date, details, type, user_id)
SELECT 1500.00, CURRENT_TIMESTAMP, 'Initial Deposit', 'CREDIT', id
FROM users WHERE username = 'testuser'
AND NOT EXISTS (
    SELECT 1 FROM transactions 
    WHERE details = 'Initial Deposit' 
    AND user_id = (SELECT id FROM users WHERE username = 'testuser')
);
