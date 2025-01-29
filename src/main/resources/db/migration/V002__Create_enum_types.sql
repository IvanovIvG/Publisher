create type two_state_stage as enum ('Ready', 'Not_Ready');
create type three_state_stage as enum ('Ready', 'In_Process', 'Not_Ready');
create type four_state_stage as enum ('Ready', 'In_Process',
    'Not_Ready_And_In_Stock', 'Not_Ready_And_Ordered');