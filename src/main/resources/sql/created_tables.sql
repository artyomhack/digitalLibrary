CREATE TABLE person (
    id int primary key,
    fullName varchar(100) unique,
    age varchar(3) not null,
    createdAt timestamp not null
);

CREATE TABLE Book (
    id int primary key,
    title varchar(55) unique,
    author varchar(100) not null,
    year_ varchar(4) not null ,
    createdAt timestamp not null,
    person_id int references person(id)
);