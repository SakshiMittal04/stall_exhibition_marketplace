-- ===========================
-- USERS
-- ===========================
INSERT INTO users (id, name, email, phone, registered_at)
VALUES
('u1', 'Rohan Kumar', 'rohan@example.com', '9876543210', CURRENT_TIMESTAMP),
('u2', 'Priya Sharma', 'priya@example.com', '9876500000', CURRENT_TIMESTAMP);

-- ===========================
-- STALLS
-- ===========================
INSERT INTO stalls (id, name, location, description)
VALUES
('s1', 'Food Corner', 'Hall A', 'Fast food and snacks'),
('s2', 'Crafts World', 'Hall B', 'Handmade crafts and souvenirs');

-- ===========================
-- STALL ITEMS
-- ===========================
INSERT INTO stall_items (id, stall_id, name, description, price, available)
VALUES
('si1', 's1', 'Burger', 'Veg Burger', 120, true),
('si2', 's1', 'French Fries', 'Crispy fries', 80, true),
('si3', 's2', 'Handmade Basket', 'Eco friendly basket', 300, true),
('si4', 's2', 'Wooden Toy', 'Handcrafted toy', 450, true);

-- ===========================
-- CARTS (1 per user)
-- ===========================
INSERT INTO carts (id, user_id, created_at, updated_at)
VALUES
('c1', 'u1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('c2', 'u2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================
-- CART ITEMS
-- ===========================
INSERT INTO cart_items (id, cart_id, stall_item_id, quantity, added_at)
VALUES
('ci1', 'c1', 'si1', 2, CURRENT_TIMESTAMP),  -- Rohan: 2 Burgers
('ci2', 'c1', 'si2', 1, CURRENT_TIMESTAMP),  -- Rohan: 1 Fries
('ci3', 'c2', 'si3', 1, CURRENT_TIMESTAMP);  -- Priya: 1 Basket

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
INSERT INTO orders
(id, user_id, stall_id, delivery_partner_id, status, total_amount, placed_at, updated_at)
VALUES
('o1', 'u1', 's1', 'd1', 'PLACED', 320, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('o2', 'u2', 's2', 'd2', 'DELIVERED', 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================
-- ORDER ITEMS
-- ===========================
INSERT INTO order_items (id, order_id, stall_item_id, quantity, price)
VALUES
('oi1', 'o1', 'si1', 2, 120),
('oi2', 'o1', 'si2', 1, 80),
('oi3', 'o2', 'si3', 1, 300);
