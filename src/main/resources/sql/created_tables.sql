CREATE TABLE person (
    id int generated by default as identity primary key,
    fullName varchar(100) not null unique ,
    age varchar(3) not null
);

CREATE TABLE book (
    id int generated by default as identity primary key,
    title varchar(55) unique unique ,
    author varchar(100) not null,
    year_ varchar(4) not null ,
    person_id INT,

    CONSTRAINT fk_person FOREIGN KEY(person_id) references person(id)
);