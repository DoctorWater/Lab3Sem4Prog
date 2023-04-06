package embrace.Service;

import embrace.Entities.Employee;
import embrace.Entities.Task;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Repos.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo repository;

    public TaskService(TaskRepo repository) {
        this.repository = repository;
    }

    public Task getById(Long id) throws NothingWasFoundException {
        Optional<Task> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NothingWasFoundException();
        }
        return result.get();
    }

    public List<Task> getByVId(Long vid){
        return repository.getTasksByEmployee_Id(vid);
    }

    public void saveOrUpdate(Task task){
        repository.save(task);
    }

    public void saveOrUpdate(List<Task> taskList){
        repository.saveAll(taskList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void delete(List<Long> ids){
        repository.deleteAllById(ids);
    }
}
