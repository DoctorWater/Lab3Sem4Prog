package embrace.Service;

import embrace.Entities.Employee;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Repos.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo repository;

    public EmployeeService(EmployeeRepo repository) {
        this.repository = repository;
    }

    public Employee getById(Long id) throws NothingWasFoundException {
        Optional<Employee> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NothingWasFoundException();
        }
        return result.get();
    }

    public void saveOrUpdate(Employee employee){
        repository.save(employee);
    }

    public void saveOrUpdate(List<Employee> employeeList){
        repository.saveAll(employeeList);
    }

    public List<Employee> getByName(String name){
        return repository.getEmployeesByName(name);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void delete(List<Long> ids){
        repository.deleteAllById(ids);
    }
}
