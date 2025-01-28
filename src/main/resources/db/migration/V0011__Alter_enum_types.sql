alter table article
    drop column chief_editor_stage,
    drop column science_editor,
    drop column author_coordination,
    drop column corrector,
    drop column page_proofs,
    drop column dummy;

drop type two_state_stage;
drop type three_state_stage;
drop type four_state_stage;

create type two_state_stage as enum('Ready', 'Not_Ready');
create type three_state_stage as enum('Ready', 'In_Process', 'Not_Ready');
create type four_state_stage as enum('Ready', 'In_Process',
    'Not_Ready_And_In_Stock', 'Not_Ready_And_Ordered');

alter table article
    add column chief_editor_stage four_state_stage default 'Not_Ready_And_Ordered',
    add column science_editor three_state_stage default 'Not_Ready',
    add column author_coordination three_state_stage default 'Not_Ready',
    add column corrector three_state_stage default 'Not_Ready',
    add column page_proofs two_state_stage default 'Not_Ready',
    add column dummy two_state_stage default 'Not_Ready';