
INSERT INTO numbers (value) SELECT DISTINCT row_number() OVER (ORDER BY (SELECT NULL)) AS value
FROM generate_series(1, 1000);

INSERT INTO factors (value) SELECT DISTINCT trunc(random() * 10000) + 1 FROM generate_series(1, 1000);

INSERT INTO users (username, password) VALUES ('John', 'password123');
INSERT INTO users (username, password) VALUES ('Wick', 'password123');
INSERT INTO users (username, password) VALUES ('Test', 'password123');