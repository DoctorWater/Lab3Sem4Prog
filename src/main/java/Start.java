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
import java.util.List;

public class Start {
    private static Repository<Employee> employeeRepository = new EmployeeRepoBatis();
    private static Repository<Task> taskRepository = new TaskRepoBatis();
    public static void main(String[] args) throws JDBCException {
        List<Employee> employees = employeeRepository.getAll();
        for (Employee employee: employees) {
            System.out.println(employee);
        }

    }
}
