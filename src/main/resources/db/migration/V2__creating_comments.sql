ALTER TABLE task ADD author varchar(100) after description;

create table employees_and_tasks.comment
(
    comment_id    bigint       not null,
    content       varchar(400) not null,
    author        varchar(100) null,
    creation_date date         not null,
    task_id       bigint       null,
    constraint table_name_pk
        primary key (comment_id),
    constraint table_name_task_task_id_fk
        foreign key (task_id) references employees_and_tasks.task (task_id)
);