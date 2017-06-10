CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE cart (
  id INT8 NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE customer (
  id         INT8 NOT NULL,
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE line_item (
  id         INT8 NOT NULL,
  cart_id    INT8 NOT NULL,
  product_id INT8 NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id          INT8       NOT NULL,
  description VARCHAR(255),
  amount      NUMERIC(19, 2),
  currency    VARCHAR(3) NOT NULL,
  title       VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE receipt (
  id          INT8       NOT NULL,
  description VARCHAR(255),
  amount      NUMERIC(19, 2),
  currency    VARCHAR(3) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE line_item
  ADD CONSTRAINT FKd62frm76cgt1fpgdjml3m1mh1
FOREIGN KEY (cart_id)
REFERENCES cart;

ALTER TABLE line_item
  ADD CONSTRAINT FK237t8tbj9haibqe7wafj4t54x
FOREIGN KEY (product_id)
REFERENCES product;
