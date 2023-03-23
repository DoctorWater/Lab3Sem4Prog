package Interfaces;

import Entities.Task;
import Exceptions.FunctionNotSupportedException;
import Exceptions.JDBCException;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
     T save(T entity) throws JDBCException;
     void deleteById(long id) throws JDBCException;
     void deleteByEntity(T entity) throws JDBCException;
     void deleteAll() throws JDBCException;
     T update(T entity) throws JDBCException;
     T getById(long id) throws JDBCException;
     List<T> getAll() throws JDBCException;
     List<T> getAllByVId(Long id) throws FunctionNotSupportedException, JDBCException;
}
