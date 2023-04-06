package embrace.Controllers;

import embrace.Annotations.TaskAnnotation;
import embrace.DTO.EmployeeDTO;
import embrace.DTO.TaskDTO;
import embrace.Entities.Employee;
import embrace.Entities.Task;
import embrace.Entities.TaskType;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Service.EmployeeService;
import embrace.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@TaskAnnotation
@RequestMapping(value = "task")
public class TaskController {
    private final TaskService service;
    private final EmployeeService secondaryService;

    public TaskController(TaskService service, EmployeeService secondaryService) {
        this.service = service;
        this.secondaryService = secondaryService;
    }

    @GetMapping(value = "/id/{id}")
    public Task getById(@PathVariable Long id) throws NothingWasFoundException {
        return service.getById(id);
    }

    @GetMapping(value = "/vid/{vid}")
    public List<Task> getByName(@PathVariable Long vid){
        return service.getByVId(vid);
    }

    @PostMapping(value = "/save/one")
    public Task saveOrUpdate(@RequestBody TaskDTO data) throws NothingWasFoundException {
        Task task = new Task(data.name(), data.deadline(), data.description(), TaskType.valueOf(data.taskType()), secondaryService.getById(data.employeeId()));
        service.saveOrUpdate(task);
        return task;
    }

    @PostMapping(value = "/save/list")
    public List<Task> saveOrUpdate(@RequestBody List<TaskDTO> data) throws NothingWasFoundException {
        List<Task> result = new ArrayList<>();
        for(TaskDTO dto: data){
            Task task = new Task(dto.name(), dto.deadline(), dto.description(), TaskType.valueOf(dto.taskType()), secondaryService.getById(dto.employeeId()));
            result.add(task);
        }
        service.saveOrUpdate(result);
        return result;
    }

    @DeleteMapping(value = "/delete/one")
    public String delete(@RequestBody Long id){
        service.delete(id);
        return "Successfully deleted";
    }

    @DeleteMapping(value = "/delete/list")
    public String delete(@RequestBody List<Long> ids){
        service.delete(ids);
        return "Successfully deleted";
    }
}
