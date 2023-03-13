package Interfaces.Mappers;

import Entities.Task;
import Entities.TaskType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TaskMapper {
    @Select("SELECT * FROM task WHERE task_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "task_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deadline", column = "deadline"),
            @Result(property = "description", column = "description"),
            @Result(property = "taskType", column = "tasktype", javaType = TaskType.class),
            @Result(property = "employee", column = "employee_id",
                    javaType = Entities.Employee.class,
                    one = @One(select = "Interfaces.Mappers.EmployeeMapper.selectEmployeeById")
            )
    })
    Task selectTaskById(Long id);

    @Select("SELECT * FROM task")
    List<Task> getAllEntities();

    @Delete("DELETE FROM task WHERE task_id = #{id}")
    void deleteById(Long id);

    @Insert("INSERT INTO task (task_id, name, deadline, description, taskType, employee_id) VALUES (#{id}, #{name}, #{deadline}, #{description}, #{taskType}, #{employee.id})")
    void insertEntity(Task task);

    @Delete("Delete from task")
    void deleteAll();

    @Update({"<script>",
            "update task",
            "  <set>",
            "    <if test='#{name} != null'>name=#{name},</if>",
            "    <if test='#{dateOfBirth} != null'>date_of_birth=#{dateOfBirth},</if>",
            "    <if test='#{deadline} != null'>deadline=#{deadline},</if>",
            "    <if test='#{description} != null'>description=#{description},</if>",
            "    <if test='#{taskType} != null'>tasktype=#{taskType},</if>",
            "    <if test='#{employee} != null'>employee_id=#{employee.getId},</if>",
            "  </set>",
            "where task_id=#{id}",
            "</script>"})
    void updateTask(Task task);
    @Select("SELECT * FROM task WHERE employee_id=#{id}")
    List<Task> getEntitiesByVid(Long id);
}
