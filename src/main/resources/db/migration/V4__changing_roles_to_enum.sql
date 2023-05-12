CREATE TABLE employees_roles
(
    employee_id BIGINT NOT NULL,
    role varchar(100) NOT NULL,
    primary key (employee_id, role)
);