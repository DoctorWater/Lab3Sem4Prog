import Entities.Employee;
import Entities.Task;
import Entities.TaskType;
import Exceptions.JDBCException;
import Interfaces.Repository;
import Repos.Hibernate.EmployeeRepoHibernate;
import Repos.Hibernate.TaskRepoHibernate;
import Repos.JDBC.EmployeeRepoJDBC;
import Repos.MyBatis.EmployeeRepoBatis;
import Repos.MyBatis.TaskRepoBatis;
import Service.HibernateUtil;

import java.sql.SQLException;
import java.util.Date;

public class Start {
    public static void main(String[] args) {
        Repository<Employee> employeeRepository = new EmployeeRepoBatis();
        Repository<Task> taskRepository = new TaskRepoBatis();
        Employee employee = new Employee();
        employee.setName("Josh");
        employee.setDateOfBirth(1238123);
        Task task = new Task();
        task.setDeadline(new Date());
        task.setTaskType(TaskType.FUNCTIONALITY);
        task.setDescription("No description.");
        task.setName("Test task");
        task.setEmployee(employee);
        try {
            employeeRepository.save(employee);
            taskRepository.save(task);
        } catch (JDBCException e) {
            throw new RuntimeException(e);
        }
    }
}
