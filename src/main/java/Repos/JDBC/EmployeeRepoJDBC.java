package Repos.JDBC;

import Entities.Employee;
import Exceptions.FunctionNotSupportedException;
import Exceptions.JDBCException;
import Interfaces.Repository;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepoJDBC implements Repository<Employee> {
    private static final String URL = "jdbc:mysql://localhost:3306/employees_and_tasks";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "HolyPreacher";

    static {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeRepoJDBC() throws SQLException {
    }

    @Override
    public Employee save(Employee entity) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (employee_id, name, date_of_birth)" + "VALUES (?,?,?)");
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getDateOfBirth());
            statement.execute();
            return entity;
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteById(long id) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM employee WHERE employee_id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteByEntity(Employee entity) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            Long id = entity.getId();
            statement.execute("DELETE FROM employee WHERE employee_id = " + id);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteAll() throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM employee");
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Employee update(Employee entity) throws JDBCException {
        try  {
            deleteByEntity(entity);
            save(entity);
            return entity;
        } catch (JDBCException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Employee getById(long id) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from employee where employee_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Employee result = new Employee();
                result.setId(rs.getLong("employee_id"));
                result.setName(rs.getString("name"));
                result.setDateOfBirth(rs.getInt("date_of_birth"));
                return result;
            }
            throw new JDBCException(new NullPointerException("No entity found."));
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public List<Employee> getAll() throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from Employee");
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> result = new ArrayList<>();
            if(rs.next()){
                Employee current = new Employee();
                current.setId(rs.getLong("employee_id"));
                current.setName(rs.getString("name"));
                current.setDateOfBirth(rs.getInt("date_of_birth"));
                result.add(current);
            }
            return result;
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public List<Employee> getAllByVId(Long id) throws FunctionNotSupportedException {
        throw new FunctionNotSupportedException();
    }
}
