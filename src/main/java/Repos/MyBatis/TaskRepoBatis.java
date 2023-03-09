package Repos.MyBatis;

import Entities.Task;
import Exceptions.JDBCException;
import Interfaces.Repository;

import java.util.List;

public class TaskRepoBatis implements Repository<Task> {
    @Override
    public Task save(Task entity) throws JDBCException {
        return null;
    }

    @Override
    public void deleteById(long id) throws JDBCException {

    }

    @Override
    public void deleteByEntity(Task entity) throws JDBCException {

    }

    @Override
    public void deleteAll() throws JDBCException {

    }

    @Override
    public Task update(Task entity) throws JDBCException {
        return null;
    }

    @Override
    public Task getById(long id) throws JDBCException {
        return null;
    }

    @Override
    public List<Task> getAll() throws JDBCException {
        return null;
    }
}
