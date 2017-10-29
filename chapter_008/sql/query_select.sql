--create table for personal
create table person (id integer primary key , name character varying);
--create table for equipment
create table equipment (id integer primary key , computer character varying, person_id integer references person(id));
--fill personal
insert into person(id, name) values ('1','alexander eremeev');
insert into person(id, name) values ('2','john doe');
insert into person(id, name) values ('3','jane doe');
insert into person(id, name) values ('4','helpdesk guy');
insert into person(id, name) values ('5', 'newbie');
--fill equipment
insert into equipment (id, computer, person_id) values ('1', 'workstation 1', '1');
insert into equipment (id, computer, person_id) values ('2', 'workstation 2', '2');
insert into equipment (id, computer, person_id) values ('3', 'workstation 3', '2');
insert into equipment (id, computer, person_id) values ('4', 'notebook 1', '3');
insert into equipment (id, computer, person_id) values ('5', 'notebook 2', '3');
insert into equipment (id, computer, person_id) values ('6', 'notebook 3', '3');
insert into equipment (id, computer, person_id) values ('7', 'workstation 4', '4');
insert into equipment (id, computer, person_id) values ('8', 'workstation 5', '4');
insert into equipment (id, computer, person_id) values ('9', 'workstation 6', '4');
insert into equipment (id, computer, person_id) values ('10', 'notebook 4', '4');
insert into equipment (id, computer, person_id) values ('11', 'notebook 5', '4');
insert into equipment (id, computer, person_id) values ('12', 'notebook 6', '4');
insert into equipment (id, person_id) values ('13', '5');
--filter personal without equipment
select p.name, e.computer from person as p inner join equipment as e on p.id = e.person_id where e.computer is null;
--filter personal by count of equipment
select p.name, count(e.computer) eq_count from person as p inner join equipment as e on p.id = e.person_id group by p.name order by eq_count desc;
--filter personal, who have notebooks
select p.name, e.computer from person as p inner join equipment as e on p.id = e.person_id where e.computer like '%notebook%';