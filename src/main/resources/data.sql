-- ===========================
-- USERS
-- ===========================
INSERT INTO users (name, email, phone, registered_at)
SELECT 'Rohan Kumar', 'rohan@example.com', '9876543210', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'rohan@example.com');

INSERT INTO users (name, email, phone, registered_at)
SELECT 'Priya Sharma', 'priya@example.com', '9876500000', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'priya@example.com');

-- ===========================
-- STALLS
-- ===========================
INSERT INTO stalls (name, location, description)
SELECT 'Food Corner', 'Hall A', 'Fast food and snacks'
WHERE NOT EXISTS (SELECT 1 FROM stalls WHERE name = 'Food Corner');

INSERT INTO stalls (name, location, description)
SELECT 'Snacks Hub', 'Hall B', 'Quick bites'
WHERE NOT EXISTS (SELECT 1 FROM stalls WHERE name = 'Snacks Hub');

INSERT INTO stalls (name, location, description)
SELECT 'Crafts World', 'Hall C', 'Handmade crafts'
WHERE NOT EXISTS (SELECT 1 FROM stalls WHERE name = 'Crafts World');

INSERT INTO stalls (name, location, description)
SELECT 'Wood Art', 'Hall D', 'Wooden artifacts'
WHERE NOT EXISTS (SELECT 1 FROM stalls WHERE name = 'Wood Art');

-- ===========================
-- STALL ITEMS (5 PER STALL)
-- ===========================

-- Food Corner
INSERT INTO stall_items (stall_id, name, description, price, available)
SELECT s.id, v.name, v.description, v.price, true
FROM stalls s
JOIN (VALUES
    ('Burger', 'Veg Burger', 120),
    ('French Fries', 'Crispy fries', 80),
    ('Pizza Slice', 'Cheese pizza', 150),
    ('Sandwich', 'Grilled sandwich', 100),
    ('Cold Drink', 'Chilled beverage', 60)
) AS v(name, description, price)
ON s.name = 'Food Corner'
WHERE NOT EXISTS (
    SELECT 1 FROM stall_items si WHERE si.name = v.name
);

-- Snacks Hub
INSERT INTO stall_items (stall_id, name, description, price, available)
SELECT s.id, v.name, v.description, v.price, true
FROM stalls s
JOIN (VALUES
    ('Nachos', 'Cheesy nachos', 140),
    ('Popcorn', 'Butter popcorn', 90),
    ('Samosa', 'Spicy samosa', 40),
    ('Spring Roll', 'Veg spring roll', 110),
    ('Milkshake', 'Vanilla shake', 130)
) AS v(name, description, price)
ON s.name = 'Snacks Hub'
WHERE NOT EXISTS (
    SELECT 1 FROM stall_items si WHERE si.name = v.name
);

-- Crafts World
INSERT INTO stall_items (stall_id, name, description, price, available)
SELECT s.id, v.name, v.description, v.price, true
FROM stalls s
JOIN (VALUES
    ('Handmade Basket', 'Eco friendly basket', 300),
    ('Clay Pot', 'Traditional clay pot', 250),
    ('Wall Hanging', 'Decorative hanging', 400),
    ('Handcrafted Lamp', 'Artisan lamp', 600),
    ('Jute Bag', 'Reusable jute bag', 350)
) AS v(name, description, price)
ON s.name = 'Crafts World'
WHERE NOT EXISTS (
    SELECT 1 FROM stall_items si WHERE si.name = v.name
);

-- Wood Art
INSERT INTO stall_items (stall_id, name, description, price, available)
SELECT s.id, v.name, v.description, v.price, true
FROM stalls s
JOIN (VALUES
    ('Wooden Toy', 'Handcrafted toy', 450),
    ('Wooden Frame', 'Photo frame', 500),
    ('Wooden Bowl', 'Polished bowl', 380),
    ('Wooden Sculpture', 'Art sculpture', 900),
    ('Key Holder', 'Wall key holder', 280)
) AS v(name, description, price)
ON s.name = 'Wood Art'
WHERE NOT EXISTS (
    SELECT 1 FROM stall_items si WHERE si.name = v.name
);

-- ===========================
-- DELIVERY PARTNERS
-- ===========================
INSERT INTO delivery_partners (name, phone, email, assigned_since)
SELECT 'Delivery Guy 1', '9000000001', 'dg1@mail.com', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM delivery_partners WHERE email = 'dg1@mail.com');

INSERT INTO delivery_partners (name, phone, email, assigned_since)
SELECT 'Delivery Guy 2', '9000000002', 'dg2@mail.com', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM delivery_partners WHERE email = 'dg2@mail.com');
