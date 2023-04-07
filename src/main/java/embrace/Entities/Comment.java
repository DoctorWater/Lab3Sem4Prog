package embrace.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(catalog = "employees_and_tasks", name = "comment")
public class Comment {
    @Id
    @Column(name="comment_id")
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private String content;
    private String author;
    @Column(name="creation_date")
    private Date creationDate = new Date();
    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    public Comment(String content, String author, Date creationDate, Task task) {
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
        this.task = task;
    }
}

/*create table employees_and_tasks.comment
(
    comment_id    bigint       not null,
    content       varchar(400) not null,
    author        varchar(100) null,
    creation_date date         not null,
    task_id       bigint       null,
    constraint table_name_pk
        primary key (comment_id),
    constraint table_name_task_task_id_fk
        foreign key (task_id) references employees_and_tasks.task (task_id)
);*/
