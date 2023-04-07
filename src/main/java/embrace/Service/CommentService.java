package embrace.Service;

import embrace.Entities.Comment;
import embrace.Entities.Task;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Repos.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepo repository;

    public CommentService(CommentRepo repository) {
        this.repository = repository;
    }

    public Comment getById(Long id) throws NothingWasFoundException {
        Optional<Comment> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NothingWasFoundException();
        }
        return result.get();
    }

    public List<Comment> getByVId(Long vid){
        return repository.getCommentsByTask_Id(vid);
    }

    public void saveOrUpdate(Comment comment){
        repository.save(comment);
    }

    public void saveOrUpdate(List<Comment> commentList){
        repository.saveAll(commentList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void delete(List<Long> ids){
        repository.deleteAllById(ids);
    }
}
