create table users
(
    username varchar(45) primary key ,
    password varchar(45) not null,
    enabled  boolean  not null
);

insert into users (username, password, enabled) values ('user', 'qwerty', 'true');
insert into users (username, password, enabled) values ('admin', 'qwerty', 'true');
