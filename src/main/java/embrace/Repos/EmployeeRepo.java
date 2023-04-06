package embrace.Repos;

import embrace.Entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    List<Employee> getEmployeesByName(String name);
}
