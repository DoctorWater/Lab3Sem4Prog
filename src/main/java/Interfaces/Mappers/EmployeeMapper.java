package Interfaces.Mappers;

import Entities.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE employee_id = #{id}")
    Employee selectEmployeeById(Long id);

    @Delete("DELETE FROM employee WHERE employee_id = #{id}")
    void deleteById(Long id);
}
