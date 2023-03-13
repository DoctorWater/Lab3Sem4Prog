package Interfaces.Mappers;

import Entities.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE employee_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "employee_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "dateOfBirth", column = "date_of_birth")
    })
    Employee selectEmployeeById(Long id);

    @Select("SELECT * FROM employee")
    @Results( value = {
            @Result(property = "dateOfBirth", column = "date_of_birth")
    })
    List<Employee> getAllEntities();

    @Delete("DELETE FROM employee WHERE employee_id = #{id}")
    void deleteById(Long id);

    @Insert("INSERT INTO employee (employee_id, name, date_of_birth) VALUES (#{id}, #{name}, #{dateOfBirth})")
    void insertEntity(Employee employee);

    @Delete("Delete from employee")
    void deleteAll();

    @Update({"<script>",
            "update Employee",
            "  <set>",
            "    <if test='#{name} != null'>name=#{name},</if>",
            "    <if test='#{dateOfBirth} != null'>date_of_birth=#{dateOfBirth},</if>",
            "  </set>",
            "where employee_id=#{id}",
            "</script>"})
    void updateEmployee(Employee employee);
}
