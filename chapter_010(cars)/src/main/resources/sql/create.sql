--create table of transmissions
create table transmission (
    id serial primary key,
    drive varchar(100) not null UNIQUE
);
--create table of gearboxes
create table gearbox (
    id serial primary key,
    shift varchar(100) not null UNIQUE
);
--create table of engines
create table engine (
    id serial primary key,
    force integer not null UNIQUE
);
--create table of car bodies
CREATE TABLE body (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) not NULL UNIQUE
);
--create table of car models
CREATE TABLE model (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) not NULL UNIQUE
);
--create table of cars
create table car (
    id serial primary key,
    name varchar(100) not null,
    transmission_id integer references transmission(id),
    gearbox_id integer references gearbox(id),
    engine_id integer references engine(id),
    model_id INTEGER REFERENCES model(id),
    body_id INTEGER REFERENCES body(id)
);
--create table of users
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) not NULL
);
--create table of user orders;
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    car_id INTEGER REFERENCES car(id),
    user_id INTEGER REFERENCES users(id),
    price INTEGER,
    mileage INTEGER,
    releaseDate TIMESTAMP,
    sold BOOLEAN
);
--create table of car images;
CREATE TABLE image (
    id SERIAL PRIMARY KEY,
    data BYTEA,
    order_id INTEGER REFERENCES orders(id)
);
