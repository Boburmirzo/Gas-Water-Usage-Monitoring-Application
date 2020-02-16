INSERT INTO measurement (id, version, amount, created, user_id, measurement_id) VALUES (1, 1, '123.4563', '2018-6-24 14:30:01', 1, 1);
INSERT INTO measurement (id, version, amount, created, user_id, measurement_id) VALUES (2, 1, '123.4562', '2018-6-24 14:30:02', 1, 2);
INSERT INTO measurement (id, version, amount, created, user_id, measurement_id) VALUES (3, 1, '123.4563', '2018-6-24 14:30:03', 1, 3);
INSERT INTO measurement (id, version, amount, created, user_id, measurement_id) VALUES (4, 1, '123.4564', '2018-6-24 14:30:04', 1, 1);

INSERT INTO users (id, version, email, password, date_of_registration) VALUES (1, 1, 'email@gmail.com', 'pass1', '2018-6-24 14:30:00');

INSERT INTO measurement_type (id, name, type) VALUES (1, 'GAS', 'DOUBLE');
INSERT INTO measurement_type (id, name, type) VALUES (2, 'HOT_WATER', 'DOUBLE');
INSERT INTO measurement_type (id, name, type) VALUES (3, 'COLD_WATER', 'DOUBLE');