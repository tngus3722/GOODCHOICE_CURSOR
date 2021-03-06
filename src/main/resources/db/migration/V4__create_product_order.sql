CREATE TABLE IF NOT EXISTS product_order
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    total_price INT NOT NULL DEFAULT 0,
    delivery_price INT NOT NULL DEFAULT 0,
    is_deleted BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);