CREATE TABLE ITEMS (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
);