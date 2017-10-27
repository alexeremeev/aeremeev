--create database

--CREATE DATABASE tracker;
--\c tracker
--Doesn't work in one script, tables will be created in default dd postgres@localhost.

-- create Roles
CREATE TABLE roles (
  id SERIAL PRIMARY KEY ,
  role CHARACTER VARYING (20) NOT NULL,
  UNIQUE (role)
);

-- create Permissions
CREATE TABLE permissions (
  id SERIAL PRIMARY KEY,
  description CHARACTER VARYING(100) NOT NULL ,
  UNIQUE (description)
);

-- create Role Rights
CREATE table role_rights(
  id SERIAL PRIMARY KEY ,
  right_id INTEGER REFERENCES permissions(id),
  role_id INTEGER REFERENCES roles(id),
  UNIQUE (right_id, role_id)
);

-- create Users
CREATE TABLE users(
  id SERIAL PRIMARY KEY ,
  login CHARACTER VARYING (100) NOT NULL ,
  password CHARACTER VARYING (100) NOT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT now(),
  role_id INTEGER REFERENCES roles(id),
  UNIQUE(login)
);

-- create Category
CREATE TABLE category (
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING (100) NOT NULL,
  UNIQUE (name)
);

-- create Status
CREATE TABLE status (
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING(100) NOT NULL,
  UNIQUE (name)
);

-- create Items
CREATE TABLE items(
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING(100) NOT NULL,
  description TEXT NOT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT now(),
  user_id INTEGER REFERENCES users(id),
  category_id INTEGER REFERENCES category(id),
  status_id INTEGER REFERENCES status(id)
);

-- create Comments
CREATE TABLE comments(
  id SERIAL PRIMARY KEY ,
  description text,
  item_id INTEGER REFERENCES items(id)
);

-- create Attachments
CREATE TABLE attachments(
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING(100) NOT NULL ,
  file bytea NOT NULL ,
  item_id INTEGER REFERENCES items(id)
);

-- Fill Roles
INSERT INTO roles (role) VALUES ('User');
INSERT INTO roles (role) VALUES ('Service Engineer');
INSERT INTO roles (role) VALUES ('Admin');

-- Fill Permissions
INSERT INTO permissions (description) VALUES ('Add');
INSERT INTO permissions (description) VALUES ('Delete');
INSERT INTO permissions (description) VALUES ('Update');

-- Fill Role Rights
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Add'), (SELECT id FROM roles WHERE role = 'User'));
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Add'), (SELECT id FROM roles WHERE role = 'Service Engineer'));
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Add'), (SELECT id FROM roles WHERE role = 'Admin'));
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Update'), (SELECT id FROM roles WHERE role = 'Service Engineer'));
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Update'), (SELECT id FROM roles WHERE role = 'Admin'));
INSERT INTO role_rights (right_id, role_id) VALUES ((SELECT  id FROM permissions WHERE description = 'Delete'), (SELECT id FROM roles WHERE role = 'Admin'));

-- Fill Users
INSERT INTO users (login, password, role_id) VALUES ('User', 'userpass', (SELECT id FROM roles WHERE role = 'User'));
INSERT INTO users (login, password, role_id) VALUES ('Engineer', 'engpass', (SELECT id FROM roles WHERE role = 'Service Engineer'));
INSERT INTO users (login, password, role_id) VALUES ('Admin', 'admpass', (SELECT id FROM roles WHERE role = 'Admin'));

-- Fill Categories
INSERT INTO category (name) VALUES ('Misc');
INSERT INTO category (name) VALUES ('Work');
INSERT INTO category (name) VALUES ('IT');

-- Fill Status
INSERT INTO status (name) VALUES ('Open');
INSERT INTO status (name) VALUES ('Processing');
INSERT INTO status (name) VALUES ('Closed');

-- Fill Items
INSERT INTO items (name, description, user_id, category_id, status_id) VALUES
  ('Test item', 'Testing sqldb by script', (SELECT id FROM users WHERE login = 'User'),
   (SELECT id from category WHERE name = 'Misc'), (SELECT id FROM status WHERE name = 'Opened'));

-- Fill Comments
INSERT INTO comments(description, item_id) VALUES ('test comment', (SELECT id from items WHERE name = 'Test item'));