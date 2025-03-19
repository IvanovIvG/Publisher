delete from flyway_schema_history where version='003';
delete from flyway_schema_history where version='002';
delete from flyway_schema_history where version='001';

drop table article;
drop table journals;
drop type two_state_stage;
drop type three_state_stage;
drop type four_state_stage;