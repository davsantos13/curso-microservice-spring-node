INSERT INTO categories (id, tx_description) VALUES (1000, 'Comics Books');
INSERT INTO categories (id, tx_description) VALUES (1001, 'Movies');
INSERT INTO categories (id, tx_description) VALUES (1002, 'Books');

INSERT INTO suppliers (id, tx_name) VALUES (1000, 'Panini Comics');
INSERT INTO suppliers (id, tx_name) VALUES (1001, 'Amazon');

INSERT INTO products (id, tx_name, fk_suppliers, fk_categories, nr_qt_available, dt_created_at) VALUES (1000, 'Crise nas Infinitas Terras', 1000, 1000, 10, CURRENT_TIMESTAMP);
INSERT INTO products (id, tx_name, fk_suppliers, fk_categories, nr_qt_available, dt_created_at) VALUES (1001, 'Interestelar', 1001, 1001, 5, CURRENT_TIMESTAMP);
INSERT INTO products (id, tx_name, fk_suppliers, fk_categories, nr_qt_available, dt_created_at) VALUES (1002, 'Harry Potter', 1001, 1002, 3, CURRENT_TIMESTAMP);