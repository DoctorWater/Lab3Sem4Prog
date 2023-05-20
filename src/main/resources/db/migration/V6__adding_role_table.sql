create table role
(
    role_id bigint auto_increment,
    type    varchar(100) not null,
    constraint role_pk
        primary key (role_id)
);
