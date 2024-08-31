-- Insert Sample Data

INSERT INTO brand (id, name) VALUES
(1, 'Beispiel Marke 1'),
(2, 'Beispiel Marke 2');

INSERT INTO unit (id, name, abbreviation) VALUES
(1, 'Kilogramm', 'kg'),
(2, 'Liter', 'l'),
(3, 'Stück', 'stk');

INSERT INTO food (id, name) VALUES
(1, 'Apfel'),
(2, 'Milch'),
(3, 'Brot');

INSERT INTO recipe (id, name, servings) VALUES
(1, 'Apfelkuchen', 8),
(2, 'Milchshake', 2);

INSERT INTO product (id, name, brand_id, quantity, unit_id) VALUES
(1, 'Grüner Apfel', 1, 10, 3),
(2, 'Vollmilch', 2, 2, 2),
(3, 'Weißbrot', 1, 5, 3);

INSERT INTO recipe_product (id, recipe_id, product_id, quantity) VALUES
(1, 1, 1, 5),  -- 5 Grüne Äpfel für Apfelkuchen
(2, 1, 3, 1),  -- 1 Weißbrot für Apfelkuchen
(3, 2, 2, 1);  -- 1 Liter Vollmilch für Milchshake
