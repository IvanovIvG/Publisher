create table article
(
    id                  uuid primary key,
    journal             uuid references journals (id) on delete cascade not null,
    name                varchar(100)                 not null,
    topic               varchar(100)                 not null,
    pages               int                          not null,
    first_page          int                          not null,
    last_page           int                          not null,
    chief_editor_stage  four_state_stage  default 'Not_Ready_And_Ordered',
    science_editor      three_state_stage default 'Not_Ready',
    author_coordination three_state_stage default 'Not_Ready',
    corrector           three_state_stage default 'Not_Ready',
    page_proofs         two_state_stage   default 'Not_Ready',
    dummy               two_state_stage   default 'Not_Ready'
);

insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-7d10-9c45-ee66c4b4aaad',
     '01950a0f-e717-750a-8c48-48eedf2e71d3',
     'firstArticle', 'topicOne', 10, 1, 10);
insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-71bf-9c31-b8de71926f53',
     '01950a0f-e717-750a-8c48-48eedf2e71d3',
     'secondArticle', 'topicOne', 10, 1, 10);
insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-7ea2-a60b-5cdaaca91069',
     '01950a0f-e717-750a-8c48-48eedf2e71d3',
     'thirdArticle', 'topicOne', 10, 1, 10);
insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-7926-bea9-bb81e3be4a8d',
     '01950a0f-e717-7193-8e4c-fa9baedd9874',
     'firstArticle', 'topicOne', 10, 1, 10);
insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-79b1-a46d-879861023fd3',
     '01950a0f-e717-7193-8e4c-fa9baedd9874',
     'secondArticle', 'topicOne', 10, 1, 10);
insert into article (id, journal, name, topic, pages, first_page, last_page) values
    ('01950a12-e683-7f78-9816-0560f6148abc',
     '01950a0f-e717-7193-8e4c-fa9baedd9874',
     'thirdArticle', 'topicOne', 10, 1, 10);
