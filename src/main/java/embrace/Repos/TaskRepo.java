package embrace.Repos;

import embrace.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> getTasksByEmployee_Id(Long id);
}
