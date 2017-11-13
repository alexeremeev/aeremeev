-- create tables
create table category (id serial primary key, name varchar(100) not null);
create table items (id serial primary key, name varchar(100) not null, category_id integer references category(id));

-- fill category
insert into category (name) values ('main');
insert into category (name) values ('secondary');
insert into category (name) values ('misc');

--fill items
insert into items (name, category_id) values ('first', 1);
insert into items (name, category_id) values ('first', 2);
insert into items (name, category_id) values ('first', 3);
insert into items (name, category_id) values ('second', 1);
insert into items (name, category_id) values ('third', 1);
insert into items (name, category_id) values ('third', 1);
insert into items (name, category_id) values ('fourth', 1);
insert into items (name, category_id) values ('first', 1);

-- select duplicates by name
select name from items group by name having count(*) > 1;

-- select duplicates by name and category_id
select name, category_id from items group by name, category_id having count(*) > 1;

-- delete duplicates by name and category_id leaving only one item with lesser id
delete from items where id not in (select min(id) from items group by name, category_id);

-- delete duplicates by name leaving only one item with lesser id
delete from items where id not in (select min(id) from items group by name);