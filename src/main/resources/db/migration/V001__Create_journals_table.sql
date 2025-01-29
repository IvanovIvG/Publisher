create table journals
(
    id   int generated always as identity primary key,
    name varchar(100) not null
);

insert into journals (name) values ('firstJournal');
insert into journals (name) values ('secondJournal');
