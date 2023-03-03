package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    private String name;
    private Integer dateOfBirth;
    /*
        create table employees_and_tasks.employee
        (
            employee_id   bigint       not null,
            name          varchar(100) not null,
            date_of_birth int          null,
            constraint employee_pk
                primary key (employee_id)
        );
    */
}
