import Entities.Employee;
import Repos.Hibernate.EmployeeRepoHibernate;
import Repos.Hibernate.TaskRepoHibernate;

public class Start {
    public static void main(String[] args) {
        var hibernateRepoEmployee = new EmployeeRepoHibernate();
        var hibernateRepoTask = new TaskRepoHibernate();
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setDateOfBirth(129);
        hibernateRepoEmployee.save(employee);
    }
}
