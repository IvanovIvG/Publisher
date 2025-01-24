delete from article;
alter table article add column last_page int not null;
alter table article add column journal int references journals(id) not null;