create table users
(
    id       uuid primary key,
    username varchar(45) not null,
    password varchar(45) not null,
    enabled  int         not null
);

insert into users (id, username, password, enabled) values ('01957f69-c3b3-7c9c-813d-8017ac0d9073', 'john', '12345', '1');
insert into users (id, username, password, enabled) values ('01957f6a-596a-723d-8e06-d24a8aaf03d7', 'bob', 'abc', '1');
