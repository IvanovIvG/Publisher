create table user_info
(
    id       uuid primary key,
    username varchar(45) unique
);

insert into user_info (id, username) values ('01958afd-67f6-7f80-bb6a-831130440862', 'user');
insert into user_info (id, username) values ('01958afd-67f6-7df4-8498-ee7572145381', 'admin');
insert into user_info (id, username) values ('c0b17a6f-6e0b-4e45-89d7-a7335b257b3b', 'bob');