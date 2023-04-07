package embrace.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(catalog = "employees_and_tasks", name = "employee")
public class Employee {
    @Id
    @Column(name="employee_id")
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private String name;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    public Employee(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(String name, String dateOfBirth) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormat);
    }

    /*
        create table employees_and_tasks.employee
        (
            employee_id   bigint       not null,
            name          varchar(100) not null,
            date_of_birth date          null,
            constraint employee_pk
                primary key (employee_id)
        );
    */
}
