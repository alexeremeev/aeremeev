create table item (id serial primary key, description varchar(100), created TIMESTAMP DEFAULT now(), done boolean DEFAULT false);