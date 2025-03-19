delete from flyway_schema_history where version='003';
delete from flyway_schema_history where version='002';
delete from flyway_schema_history where version='001';

drop table user_info;
drop table users;
drop table authorities;