package Repos.JDBC;

import Entities.Task;
import Exceptions.JDBCException;
import Interfaces.Repository;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepoJDBC implements Repository<Task> {
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

    public TaskRepoJDBC() throws SQLException {
    }


    @Override
    public Task save(Task entity) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO Task (Task_id, name, deadline, description, tasktype, employee_id)" + "VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setDate(3, (Date) entity.getDeadline());
            statement.setString(4, entity.getDescription());
            statement.setString(5, entity.getTaskType());
            statement.setLong(6, entity.getEmployee().getId());
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
            String sql = "DELETE FROM Task WHERE Task_id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteByEntity(Task entity) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            Long id = entity.getId();
            statement.execute("DELETE FROM Task WHERE Task_id = " + id);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public void deleteAll() throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM task");
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Task update(Task entity) throws JDBCException {
        try {
            deleteByEntity(entity);
            save(entity);
            return entity;
        } catch (JDBCException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Task getById(long id) throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from Task where task_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            EmployeeRepoJDBC employeeRepoJDBC = new EmployeeRepoJDBC();
            if (rs.next()) {
                Task result = new Task();
                result.setId(rs.getLong("task_id"));
                result.setName(rs.getString("name"));
                result.setDeadline(rs.getDate("deadline"));
                result.setDescription(rs.getString("description"));
                result.setTaskType(rs.getString("tasktype"));
                result.setEmployee(employeeRepoJDBC.getById(rs.getLong("employee_id")));
                return result;
            }
            throw new JDBCException(new NullPointerException("No entity found."));
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public List<Task> getAll() throws JDBCException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from Task");
            ResultSet rs = ps.executeQuery();
            ArrayList<Task> result = new ArrayList<>();
            EmployeeRepoJDBC employeeRepoJDBC = new EmployeeRepoJDBC();
            if (rs.next()) {
                Task current = new Task();
                current.setId(rs.getLong("task_id"));
                current.setName(rs.getString("name"));
                current.setDeadline(rs.getDate("deadline"));
                current.setDescription(rs.getString("description"));
                current.setTaskType(rs.getString("tasktype"));
                current.setEmployee(employeeRepoJDBC.getById(rs.getLong("employee_id")));
                result.add(current);
            }
            return result;
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }
}
