alter table article drop column journal;
alter table article add column journal int references journals(id) on delete cascade not null ;