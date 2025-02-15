create table journals
(
    id   uuid primary key,
    name varchar(100) not null,
    number varchar(100) not null
);

insert into journals (id, name, number) values ('01950a0f-e717-750a-8c48-48eedf2e71d3', 'firstJournal', '01/25');
insert into journals (id, name, number) values ('01950a0f-e717-7193-8e4c-fa9baedd9874', 'secondJournal', '01/25');
