create table users
(
    username varchar(45) primary key ,
    password varchar(45) not null,
    enabled  int         not null
);

insert into users (username, password, enabled) values ('john', '12345', '1');
insert into users (username, password, enabled) values ('bob', 'abc', '1');
