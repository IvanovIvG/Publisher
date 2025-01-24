create table article(
    id int generated always as identity primary key,
    name varchar(100) not null,
    topic varchar(100) not null,
    pages int not null,
    first_page int not null,
    chief_editor_stage four_state_stage default 'not ready and ordered',
    science_editor three_state_stage default 'not ready',
    author_coordination three_state_stage default 'not ready',
    corrector three_state_stage default 'not ready',
    page_proofs two_state_stage default 'not ready',
    dummy two_state_stage default 'not ready'
)