create table employees_and_tasks.employee
(
    employee_id   bigint       not null,
    name          varchar(100) not null,
    date_of_birth date          null,
    constraint employee_pk
        primary key (employee_id)
);

create table employees_and_tasks.task
(
    task_id     bigint       not null,
    name        varchar(100) not null,
    deadline    date         null,
    description varchar(400) null,
    tasktype    varchar(100) not null,
    employee_id bigint       not null,
    constraint task_pk
        primary key (task_id),
    constraint task_employee_employee_id_fk
        foreign key (employee_id) references employees_and_tasks.employee (employee_id)
);
