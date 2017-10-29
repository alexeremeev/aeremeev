--create table of transmissions
create table transmission (
    id serial primary key,
    drive varchar(100) not null
);
--create table of gearboxes
create table gearbox (
    id serial primary key,
    shift varchar(100) not null
);
--create table of engines
create table engine (
    id serial primary key,
    force integer not null
);
--create table of cars
create table car (
    id serial primary key,
    name varchar(100) not null,
    transmission_id integer references transmission(id),
    gearbox_id integer references gearbox(id),
    engine_id integer references engine(id)
);
--fill transmissions
insert into transmission (drive) values ('front');
insert into transmission (drive) values ('rear');
insert into transmission (drive) values ('fourwheel');

--fill gearboxes
insert into gearbox (shift) values ('mechanical');
insert into gearbox (shift) values ('auto');
insert into gearbox (shift) values ('robot');
insert into gearbox (shift) values ('variator');

--fill engines
insert into engine (force) values ('70');
insert into engine (force) values ('90');
insert into engine (force) values ('108');
insert into engine (force) values ('160');

--fill cars
insert into car (name, transmission_id, gearbox_id, engine_id) values (
    'Skoda Fabia',
    '1',
    '3',
    '3'
);

insert into car (name, transmission_id, gearbox_id, engine_id) values (
    'Nissan Juke',
    '1',
    '1',
    '1'
);

insert into car (name, transmission_id, gearbox_id, engine_id) values (
    'Honda Civic',
    '1',
    '2',
    '4'
);

--filter all created cars
select c.id, c.name, t.drive, g.shift, e.force
from car as c, transmission as t, gearbox as g, engine as e
where c.transmission_id = t.id
and c.gearbox_id = g.id
and c.engine_id = e.id;

--filter all unused transmissions
select t.drive from car c right outer join transmission as t on c.transmission_id = t.id where c.name is null;

--filter all unused gearboxes
select g.shift from car c right outer join gearbox as g on c.gearbox_id = g.id where c.name is null;

--filter all unused engines
select e.force from car c right outer join engine as e on c.engine_id = e.id where c.name is null;