create table users
(
    username varchar(45) primary key ,
    password varchar(45) not null,
    enabled  boolean  not null
);

insert into users (username, password, enabled) values ('john', '12345', 'true');
insert into users (username, password, enabled) values ('bob', 'abc', 'true');
