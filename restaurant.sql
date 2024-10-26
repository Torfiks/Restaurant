SET TIME ZONE '+00:00';


CREATE TABLE dishes (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL,
    price integer NOT NULL,
    enabled boolean NOT NULL
);


INSERT INTO dishes (id, name, price, enabled) VALUES
  (1, 'Beef Curry', 2000, TRUE),
  (2, 'Chicken Curry', 2500, TRUE),
  (3, 'Pork Curry', 3000, TRUE);


CREATE TABLE orders (
    id serial PRIMARY KEY,
    table_id integer NOT NULL,
    dish_id integer NOT NULL,
    price integer NOT NULL,
    count integer NOT NULL,
);

CREATE TABLE c (
    id serial PRIMARY KEY,
    dish_id integer NOT NULL,
    present integer NOT NULL,
    price integer NOT NULL,
);

INSERT INTO orders (id, table_id, dish_id, price, count, enabled, created_at) VALUES
  (1, 2, 1, 2000, 3, TRUE, '2019-05-16 10:28:33'),
  (2, 2, 2, 2000, 2, TRUE, '2019-05-16 10:28:33'),
  (3, 1, 2, 2000, 2, TRUE, '2019-05-16 10:28:33'),
  (4, 1, 2, 2000, 5, TRUE, '2019-05-14 17:30:00');


CREATE TABLE tables (
    id serial PRIMARY KEY,
    enabled boolean NOT NULL,
    charge integer NOT NULL,
    chairs integer NOT NULL,
    nameUser varchar(1000)
);


INSERT INTO tables (id, enabled, charge, chairs) VALUES
     (1, TRUE, 100, 4),
     (2, TRUE, 2000, 6),
     (3, FALSE, 3000, 8);


CREATE TABLE users (
   id serial PRIMARY KEY,
   username varchar(100) NOT NULL,
   password varchar(100) NOT NULL,
   enabled boolean NOT NULL,
   role integer NOT NULL

);


INSERT INTO users (id, username, password, enabled, role) VALUES
    (1, 'aa', '123', TRUE, 2);