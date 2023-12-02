CREATE TABLE person (
    id serial primary key,
    fullName varchar(100) not null ,
    age varchar(3) not null
);

CREATE TABLE Book (
    id serial primary key,
    title varchar(55) unique,
    author varchar(100) not null,
    year_ varchar(4) not null ,
    person_id serial references person(id)
);