package hibernate.dao.daoimpl;

import hibernate.dao.dao.CounterDao;
import hibernate.entities.Counter;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CounterdaoImpl implements CounterDao {
    @Override
    public List<Counter> getAllCounters() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from counter";
            return session.createQuery(hql,Counter.class).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Counter getCounterById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
          return session.get(Counter.class,id);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Counter getCounterByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from counter where countername=:name";
            return session.createQuery(hql,Counter.class).setParameter("name",name).getSingleResult();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<String> getAllCounterNames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="countername from counter";
            return session.createQuery(hql,String.class).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int saveCounter(Counter counter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            if(counter.getId()==0)
            {
                session.save(counter);
                session.getTransaction().commit();
                return 1;
            }
            else {
                session.update(counter);
                session.getTransaction().commit();
                return 2;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Character getCounterBillInitial(int id) {
        Counter counter = getCounterById(id);
        if(counter!=null)
            return counter.getBillinitial();
        else
            return null;
    }
    @Override
    public Counter getCounterByPerson(String person) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from counter where person=:person";
            return session.createQuery(hql,Counter.class).setParameter("person",person).getSingleResult();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
