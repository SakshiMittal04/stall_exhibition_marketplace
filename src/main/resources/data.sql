-- ===========================
-- USERS (VARCHAR ID)
-- ===========================
INSERT INTO users (id, name, email, phone, registered_at)
VALUES
('u1', 'Rohan Kumar', 'rohan@example.com', '9876543210', CURRENT_TIMESTAMP),
('u2', 'Priya Sharma', 'priya@example.com', '9876500000', CURRENT_TIMESTAMP);

-- ===========================
-- STALLS (BIGINT ID – MANUAL)
-- ===========================
-- stalls.id is BIGINT and NOT identity → must provide ID
INSERT INTO stalls (id, name, location, description)
VALUES
(1, 'Food Corner', 'Hall A', 'Fast food and snacks'),
(2, 'Snacks Hub', 'Hall B', 'Quick bites'),
(3, 'Crafts World', 'Hall C', 'Handmade crafts'),
(4, 'Wood Art', 'Hall D', 'Wooden artifacts');

-- ===========================
-- STALL ITEMS (BIGINT ID – IDENTITY)
-- ===========================
-- id is auto-generated → DO NOT provide id
INSERT INTO stall_items (stall_id, name, description, price, available)
VALUES
(1, 'Burger', 'Veg Burger', 120, true),
(1, 'French Fries', 'Crispy fries', 80, true),
(3, 'Handmade Basket', 'Eco friendly basket', 300, true),
(4, 'Wooden Toy', 'Handcrafted toy', 450, true);

-- ===========================
-- CARTS (VARCHAR ID)
-- ===========================
INSERT INTO carts (id, user_id, created_at, updated_at)
VALUES
('c1', 'u1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('c2', 'u2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================
-- CART ITEMS
-- ===========================
-- stall_item_id is BIGINT → must be numeric
INSERT INTO cart_items (id, cart_id, stall_item_id, quantity, added_at)
VALUES
('ci1', 'c1', 1, 2, CURRENT_TIMESTAMP), -- Burger
('ci2', 'c1', 2, 1, CURRENT_TIMESTAMP), -- Fries
('ci3', 'c2', 3, 1, CURRENT_TIMESTAMP); -- Basket

-- ===========================
-- DELIVERY PARTNERS
-- ===========================
INSERT INTO delivery_partners (id, name, phone, email, assigned_since)
VALUES
('d1', 'Delivery Guy 1', '9000000001', 'dg1@mail.com', CURRENT_TIMESTAMP),
('d2', 'Delivery Guy 2', '9000000002', 'dg2@mail.com', CURRENT_TIMESTAMP);

-- ===========================
-- ORDERS
-- ===========================
-- stall_id is BIGINT → numeric
INSERT INTO orders
(id, user_id, stall_id, delivery_partner_id, status, total_amount, placed_at, updated_at)
VALUES
('o1', 'u1', 1, 'd1', 'PLACED', 320, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('o2', 'u2', 3, 'd2', 'DELIVERED', 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================
-- ORDER ITEMS
-- ===========================
-- stall_item_id is BIGINT → numeric
INSERT INTO order_items (id, order_id, stall_item_id, quantity, price)
VALUES
('oi1', 'o1', 1, 2, 120),
('oi2', 'o1', 2, 1, 80),
('oi3', 'o2', 3, 1, 300);
