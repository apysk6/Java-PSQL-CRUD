DROP TABLE Person;
 
CREATE TABLE Person(
    id      SERIAL  PRIMARY KEY,
    name        VARCHAR(25) NOT NULL,
    surname     VARCHAR(25) NOT NULL,
    birth_date  DATE    NOT NULL,
    position    VARCHAR(25) NOT NULL,
    salary      INTEGER  NOT NULL,
    team        VARCHAR(25)
);
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Artur', 'Misiewicz', '1997-03-03', 'IT Assistant', 4000, 'IT');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Michał', 'Bogdanowicz', '1987-02-16', 'Consultant', 2500, 'Helpdesk');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Antoni', 'Ceregielski', '1980-01-11', 'Consultant', 3000, 'Helpdesk');
   
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Paulina', 'Kuchcińska', '1995-06-12', 'Programmer', 3000, 'Programmers');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Bartosz', 'Smith', '1978-09-11', 'Team Leader', 6000, 'Programmers');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Kate', 'Morningstar', '1988-12-12', 'Accountant', 3000, 'Accountants');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Artur', 'Pyśk', '1997-03-03', 'CEO', 25000, 'Management');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Ireneusz', 'Orzechowski', '1997-06-15', 'Lead Accountant', 10000, 'Accountants');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Michał', 'Syriusz', '1992-04-24', 'Accountant', 4000, 'Accountants');
 
INSERT INTO Person(name, surname, birth_date, position, salary, team)
VALUES ('Beata', 'Klasa', '1991-01-16', 'HR Consultant', 3000, 'HR');
