package Repos.MyBatis;

import Entities.Employee;
import Exceptions.JDBCException;
import Interfaces.Repository;
import Service.BatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.List;

public class EmployeeRepoBatis implements Repository<Employee> {
    private static SqlSessionFactory sessionFactory;

    static {
        try {
            sessionFactory = BatisUtil.getSessionFactory("resources/mybatis-config.xml");
        } catch (IOException e) {
            System.out.println("MyBatis configuration file's name is wrong.");
        }
    }

    @Override
    public Employee save(Employee entity) throws JDBCException {
        return null;
    }

    @Override
    public void deleteById(long id) throws JDBCException {

    }

    @Override
    public void deleteByEntity(Employee entity) throws JDBCException {

    }

    @Override
    public void deleteAll() throws JDBCException {

    }

    @Override
    public Employee update(Employee entity) throws JDBCException {
        return null;
    }

    @Override
    public Employee getById(long id) throws JDBCException {
        return null;
    }

    @Override
    public List<Employee> getAll() throws JDBCException {
        return null;
    }
}
