package embrace.Service;

import embrace.Entities.Employee;
import embrace.Entities.Role;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Repos.EmployeeRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepo repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepo repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    public Employee getById(Long id) throws NothingWasFoundException {
        Optional<Employee> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new NothingWasFoundException();
        }
        return result.get();
    }

    public void saveOrUpdate(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        assignDefaultRoles(employee);
        repository.save(employee);
    }

    public void saveOrUpdate(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            assignDefaultRoles(employee);
        }
        repository.saveAll(employeeList);
    }

    public List<Employee> getByName(String name) {
        return repository.getEmployeesByName(name);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    private void assignDefaultRoles(Employee employee) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Role.RoleEnum.USER));
        employee.setRoles(roles);
    }
}
