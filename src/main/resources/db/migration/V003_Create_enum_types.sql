create type two_state_stage as enum('ready', 'not ready');
create type three_state_stage as enum('ready', 'in process', 'not ready');
create type four_state_stage as enum('ready', 'in process',
    'not ready and in stock', 'not ready and ordered');