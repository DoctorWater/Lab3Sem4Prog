package Repos.JDBC;

import Entities.Employee;
import Exceptions.JDBCException;
import Interfaces.Repository;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class EmployeeRepoJDBC implements Repository<Employee> {
    private static final String URL = "jdbc:mysql://localhost:3306/";
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
            String sql = "INSERT INTO Employee (employee_id, name, date_of_birth)" + "VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getEmployee_id());
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
            String sql = "DELETE FROM Employee WHERE employee_id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteByEntity(Employee entity) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            Long id = entity.getEmployee_id();
            statement.execute("DELETE FROM Employee WHERE employee_id = " + id);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteAll() throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE * FROM Employee");
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Employee update(Employee entity) {
        return null;
    }

    @Override
    public Employee getById(long id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
