CREATE TABLE IF NOT EXISTS users (
	 id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	name VARCHAR(60) NOT NULL,
	email VARCHAR(60) NOT NULL,
	current_balance DECIMAL(10,2) NOT NULL DEFAULT 10000.00
);

CREATE TABLE IF NOT EXISTS transactions (
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	crypto_symbol VARCHAR(8) NOT NULL,
	quantity DECIMAL(20, 2) NOT NULL,
	price_at_transaction DECIMAL(20, 2) NOT NULL,
	transaction_type VARCHAR(4) NOT NULL,
	timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_transaction_user_id
		FOREIGN KEY (user_id)
		REFERENCES users (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS holdings (
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	crypto_symbol VARCHAR(8) NOT NULL,
	quantity DECIMAL(20, 6) NOT NULL DEFAULT 0.00,

	CONSTRAINT fk_holding_user_id
		FOREIGN KEY (user_id)
		REFERENCES users (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,

	UNIQUE (user_id, crypto_symbol)
);