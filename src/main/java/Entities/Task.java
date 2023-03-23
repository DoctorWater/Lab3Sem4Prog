package Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(schema = "employees_and_tasks", name = "task")
public class Task {
    @Id
    @Column(name="task_id")
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private String name;
    private Date deadline;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskType taskType;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    /*
    create table employees_and_tasks.task
    (
        task_id     bigint       not null,
        name        varchar(100) not null,
        deadline    date         null,
        description varchar(400)          null,
        tasktype    varchar(100) not null,
        employee_id bigint       not null,
        constraint task_pk
            primary key (task_id),
        constraint task_employee_employee_id_fk
            foreign key (employee_id) references employees_and_tasks.employee (employee_id)
    );

*/
}
