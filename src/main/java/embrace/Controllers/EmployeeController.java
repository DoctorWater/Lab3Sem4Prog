package embrace.Controllers;
import embrace.Annotations.EmployeeAnnotation;
import embrace.DTO.EmployeeDTO;
import embrace.Entities.Employee;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@EmployeeAnnotation
@RequestMapping(value = "employee")
public class EmployeeController{
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "/id/{id}")
    public Employee getById(@PathVariable Long id) throws NothingWasFoundException {
        return service.getById(id);
    }

    @GetMapping(value = "/name/{name}")
    public List<Employee> getByName(@PathVariable String name){
        return service.getByName(name);
    }

    @PostMapping(value = "/save/one")
    public Employee saveOrUpdate(@RequestBody EmployeeDTO data) throws ParseException {
        Employee newbie = new Employee(data.name(), data.dateOfBirth());
        service.saveOrUpdate(newbie);
        return newbie;
    }

    @PostMapping(value = "/save/list")
    public List<Employee> saveOrUpdate(@RequestBody List<EmployeeDTO> data) throws ParseException {
        List<Employee> result = new ArrayList<>();
        for(EmployeeDTO dto: data){
            Employee newbie = new Employee(dto.name(), dto.dateOfBirth());
            result.add(newbie);
        }
        service.saveOrUpdate(result);
        return result;
    }

    @DeleteMapping(value = "/delete/one/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "Successfully deleted";
    }

    @DeleteMapping(value = "/delete/list")
    public String delete(@RequestBody List<Long> ids){
        service.delete(ids);
        return "Successfully deleted";
    }
}
