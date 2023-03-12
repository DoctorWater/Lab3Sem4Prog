package Repos.MyBatis;

import Entities.Task;
import Exceptions.FunctionNotSupportedException;
import Exceptions.JDBCException;
import Interfaces.Mappers.EmployeeMapper;
import Interfaces.Mappers.TaskMapper;
import Interfaces.Repository;
import Service.BatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.List;

public class TaskRepoBatis implements Repository<Task> {
    private static SqlSessionFactory sessionFactory;

    static {
        try {
            sessionFactory = BatisUtil.getSessionFactory();
        } catch (IOException e) {
            System.out.println("MyBatis configuration file's name is wrong.");
        }
    }
    @Override
    public Task save(Task entity){
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        mapper.insertEntity(entity);
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(Task entity) {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        mapper.deleteById(entity.getId());
        session.commit();
        session.close();
    }

    @Override
    public void deleteAll()  {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        mapper.deleteAll();
        session.commit();
        session.close();
    }

    @Override
    public Task update(Task entity)  {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        mapper.updateTask(entity);
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public Task getById(long id)  {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        Task result = mapper.selectTaskById(id);
        session.close();
        return result;
    }

    @Override
    public List<Task> getAll()  {
        var session = sessionFactory.openSession();
        TaskMapper mapper = session.getMapper(TaskMapper.class);
        List<Task> result = mapper.getAllEntities();
        session.close();
        return result;
    }

    @Override
    public List<Task> getAllByVId(Long id) {
        return null;
    }
}
