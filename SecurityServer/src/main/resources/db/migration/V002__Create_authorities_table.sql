create table authorities
(
    username  varchar(45) primary key ,
    authority varchar(45) not null
);

insert into authorities (username, authority) values ('john', 'ROLE_USER');
insert into authorities (username, authority) values ('bob', 'ROLE_ADMIN');