package embrace.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(catalog = "employees_and_tasks", name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ToString.Exclude
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name="employees_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name="role")
    private Collection<Role> roles;
    @Column(name="password")
    private String password;


    public Employee(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(String name, String dateOfBirth) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormat);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(employee.getDateOfBirth()) : employee.getDateOfBirth() != null)
            return false;
        if (getRoles() != null ? !getRoles().equals(employee.getRoles()) : employee.getRoles() != null) return false;
        return getPassword() != null ? getPassword().equals(employee.getPassword()) : employee.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }
}
