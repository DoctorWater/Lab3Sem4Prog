package Repos.Hibernate;

import Entities.Employee;
import Entities.Task;
import Interfaces.Repository;
import Service.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskRepoHibernate implements Repository<Task> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Task save(Task entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Task employee = session.getReference(Task.class, id);
        if (employee != null) {
            session.remove(employee);
            session.flush();
        }
    }

    @Override
    public void deleteByEntity(Task entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
        session.flush();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Task");
        query.executeUpdate();
    }

    @Override
    public Task update(Task entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public Task getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    @Override
    public List<Task> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Task a", Task.class).getResultList();
    }

    public List<Task> getAllByVId(Long id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Task a where a.employee.id = id", Task.class).getResultList();
    }
}
