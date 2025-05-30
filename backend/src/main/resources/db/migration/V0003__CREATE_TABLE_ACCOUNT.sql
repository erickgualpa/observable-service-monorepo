CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entity_id CHAR(36) NOT NULL,
    currency CHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    wallet INT NOT NULL,
    wallet_entity_id CHAR(36) NOT NULL,
    UNIQUE KEY uk_account_entity_id(entity_id),
    UNIQUE KEY uk_account_wallet_entity_id(wallet_entity_id)
);