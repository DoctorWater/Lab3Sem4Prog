package embrace.Repos;

import embrace.Entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> getTasksByEmployee_Id(Long id);
}
