--CREATE USER usc WITH PASSWORD 'psc';

--CREATE DATABASE shoppingcart;
GRANT ALL PRIVILEGES ON DATABASE shoppingcart TO usc;

CREATE TABLE customer(
customer_id SERIAL PRIMARY KEY,
customer_type TEXT NOT NULL
);

CREATE TABLE product_price(
product_price_id SERIAL PRIMARY KEY,
product TEXT NOT NULL,
price NUMERIC(10,2) NOT NULL
);

CREATE TABLE cart(
cart_id SERIAL PRIMARY KEY,
customer INTEGER REFERENCES customer,
total_before_discount NUMERIC(10,2),
discount NUMERIC(10,2),
total NUMERIC(10,2)
);

CREATE TABLE cart_item(
cart_item_id SERIAL PRIMARY KEY,
quantity NUMERIC(15,4) NOT NULL,
product_price INTEGER REFERENCES product_price,
cart INTEGER REFERENCES cart
);
