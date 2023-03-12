package Repos.MyBatis;

import Entities.Employee;
import Exceptions.FunctionNotSupportedException;
import Exceptions.JDBCException;
import Interfaces.Mappers.EmployeeMapper;
import Interfaces.Repository;
import Service.BatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.List;

public class EmployeeRepoBatis implements Repository<Employee> {
    private static SqlSessionFactory sessionFactory;

    static {
        try {
            sessionFactory = BatisUtil.getSessionFactory();
        } catch (IOException e) {
            System.out.println("MyBatis configuration file's name is wrong.");
        }
    }

    @Override
    public Employee save(Employee entity){
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.insertEntity(entity);
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id){
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(Employee entity)  {
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.deleteById(entity.getId());
        session.commit();
        session.close();
    }

    @Override
    public void deleteAll()  {
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.deleteAll();
        session.commit();
        session.close();
    }

    @Override
    public Employee update(Employee entity)  {
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.updateEmployee(entity);
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public Employee getById(long id)  {
        var session = sessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        session.close();
        return mapper.selectEmployeeById(id);
    }

    @Override
    public List<Employee> getAll()  {
        return null;
    }

    @Override
    public List<Employee> getAllByVId(Long id) throws FunctionNotSupportedException {
        throw new FunctionNotSupportedException();
    }
}
