package embrace.DTO;

import java.util.Date;

public record CommentDTO(Long id, String content, String author, Date creationDate, Long task_id) {
}
