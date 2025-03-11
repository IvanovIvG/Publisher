create table authorities
(
    id        uuid primary key,
    username  varchar(45) not null,
    authority varchar(45) not null
);

insert into authorities (id, username, authority) values ('01957f6a-596a-7676-b1e8-25a1e00f788a', 'john', 'ROLE_READ');
insert into authorities (id, username, authority) values ('01957f6a-f6d0-7442-bc2f-3f105d9ec4a9', 'bob', 'ROLE_ADMIN');