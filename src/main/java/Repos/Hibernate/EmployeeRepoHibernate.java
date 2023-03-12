package Repos.Hibernate;

import Entities.Employee;
import Entities.Task;
import Exceptions.FunctionNotSupportedException;
import Interfaces.Repository;
import Service.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeRepoHibernate implements Repository<Employee> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Employee save(Employee entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(entity);
        session.flush();
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.getReference(Employee.class, id);
        if (employee != null) {
            session.remove(employee);
            session.flush();
        }
    }

    @Override
    public void deleteByEntity(Employee entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
        session.flush();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Employee");
        query.executeUpdate();
    }

    @Override
    public Employee update(Employee entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public Employee getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
    }

    @Override
    public List<Employee> getAllByVId(Long id) throws FunctionNotSupportedException {
        throw new FunctionNotSupportedException();
    }
}
