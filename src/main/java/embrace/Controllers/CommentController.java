package embrace.Controllers;

import embrace.DTO.CommentDTO;
import embrace.Entities.Comment;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Service.CommentService;
import embrace.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "comment")
public class CommentController {
    private final CommentService service;
    private final TaskService secondaryService;
    public CommentController(CommentService service, TaskService secondaryService) {
        this.service = service;
        this.secondaryService = secondaryService;
    }

    @GetMapping(value = "/id/{id}")
    public Comment getById(@PathVariable Long id) throws NothingWasFoundException {
        return service.getById(id);
    }

    @GetMapping(value = "/vid/{vid}")
    public List<Comment> getByName(@PathVariable Long vid){
        return service.getByVId(vid);
    }

    @PostMapping(value = "/save/one")
    public Comment saveOrUpdate(@RequestBody CommentDTO data) throws NothingWasFoundException {
        Comment task = new Comment(data.content(), data.author(), data.creationDate(), secondaryService.getById(data.task_id()));
        service.saveOrUpdate(task);
        return task;
    }

    @PostMapping(value = "/save/list")
    public List<Comment> saveOrUpdate(@RequestBody List<CommentDTO> data) throws NothingWasFoundException {
        List<Comment> result = new ArrayList<>();
        for(CommentDTO dto: data){
            Comment comment = new Comment(dto.content(), dto.author(), dto.creationDate(), secondaryService.getById(dto.task_id()));
            result.add(comment);
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
