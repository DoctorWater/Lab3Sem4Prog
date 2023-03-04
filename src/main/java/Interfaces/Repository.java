package Interfaces;

import Exceptions.JDBCException;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
     T save(T entity) throws JDBCException;
     void deleteById(long id) throws JDBCException;
     void deleteByEntity(T entity) throws JDBCException;
     void deleteAll() throws JDBCException;
     T update(T entity);
     T getById(long id);
     List<T> getAll();
}
