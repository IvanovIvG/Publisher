create table article
(
    id                  int generated always as identity primary key,
    journal             int references journals (id) on delete cascade not null,
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

insert into article (journal, name, topic, pages, first_page, last_page) values
    (1, 'firstArticle', 'topicOne', 10, 1, 10);
insert into article (journal, name, topic, pages, first_page, last_page) values
    (1, 'secondArticle', 'topicOne', 10, 1, 10);
insert into article (journal, name, topic, pages, first_page, last_page) values
    (1, 'thirdArticle', 'topicOne', 10, 1, 10);
insert into article (journal, name, topic, pages, first_page, last_page) values
    (2, 'firstArticle', 'topicOne', 10, 1, 10);
insert into article (journal, name, topic, pages, first_page, last_page) values
    (2, 'secondArticle', 'topicOne', 10, 1, 10);
insert into article (journal, name, topic, pages, first_page, last_page) values
    (2, 'thirdArticle', 'topicOne', 10, 1, 10);
