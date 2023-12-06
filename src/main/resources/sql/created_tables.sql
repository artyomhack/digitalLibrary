CREATE TABLE person (
    id serial primary key,
    fullName varchar(100) not null ,
    age varchar(3) not null
);

CREATE TABLE book (
    id serial primary key,
    title varchar(55) unique,
    author varchar(100) not null,
    year_ varchar(4) not null ,
    person_id INT,

    CONSTRAINT fk_person FOREIGN KEY(person_id) references person(id)
);