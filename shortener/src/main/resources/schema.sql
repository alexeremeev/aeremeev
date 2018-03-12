create table IF NOT EXISTS users (
  id IDENTITY PRIMARY KEY,
  username varchar(100) not null unique,
  password varchar(100) not null,
  enabled boolean default true);

create table IF NOT EXISTS urls(
  id IDENTITY PRIMARY KEY,
  original_url varchar(256) not null,
  short_url varchar(50) not null,
  counter INTEGER,
  user_id integer references users(id));