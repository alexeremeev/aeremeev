CREATE TABLE company (
  id integer NOT NULL,
  name varchar,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
  id integer NOT NULL,
  name varchar,
  company_id integer,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO person (id, name, company_id) VALUES (1, 'Employee 1', 1);
INSERT INTO person (id, name, company_id) VALUES (3, 'Employee 3', 2);
INSERT INTO person (id, name, company_id) VALUES (4, 'Employee 4', 2);
INSERT INTO person (id, name, company_id) VALUES (5, 'Employee 5', 3);
INSERT INTO person (id, name, company_id) VALUES (6, 'Employee 6', 3);
INSERT INTO person (id, name, company_id) VALUES (7, 'Employee 7', 4);
INSERT INTO person (id, name, company_id) VALUES (8, 'Employee 8', 4);
INSERT INTO person (id, name, company_id) VALUES (9, 'Employee 9', 4);
INSERT INTO person (id, name, company_id) VALUES (10, 'Employee 10', 5);

SELECT P.NAME, C.NAME FROM PERSON AS P RIGHT OUTER JOIN COMPANY AS C ON P.COMPANY_ID = C.ID WHERE C.ID <> 5;

SELECT C.NAME, COUNT(P.NAME) COUNT_OF_EMPLOYEES FROM COMPANY AS C
  LEFT OUTER JOIN PERSON AS P ON C.ID = P.COMPANY_ID
  GROUP BY C.NAME ORDER BY 2 DESC LIMIT 1;
