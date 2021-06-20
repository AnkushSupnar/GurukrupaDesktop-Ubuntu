package hibernate.dao.daoimpl;

import hibernate.dao.dao.CustomerPassbookDao;
import hibernate.entities.CustomerPassbook;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

public class CustomerPassbookDaoImpl implements CustomerPassbookDao {
    @Override
    public List<CustomerPassbook> getAllCustomerPassbook() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from CustomerPassbook";
        return session.createQuery(hql,CustomerPassbook.class).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomerPassbook getCustomerPassbookById(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            return session.get(CustomerPassbook.class,id);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomerPassbook getCustomerPassbookByBillNo(long billno) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "from CustomerPassbook where trid=:billno and particulars=:parti";
                CustomerPassbook book = null;
                try{
                    book = session.createQuery(hql,CustomerPassbook.class).
                                    setParameter("billno",billno).
                                    setParameter("parti","Bill no "+billno).getSingleResult();
                }catch(NoResultException nr)
                {
                    return null;
                }
            return book;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerPassbook> getCustomerPassbookbyCustomer(long customerId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from CustomerPassbook where customerid=:id";
            return session.createQuery(hql,CustomerPassbook.class).
                    setParameter("id",customerId).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<CustomerPassbook> getCustomerPassbookbyByDatePeriod(long customerId, LocalDate start, LocalDate end) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
        String hql="from CustomerPassbook where customerid=:id and date between :start and :end";
        return session.createQuery(hql,CustomerPassbook.class).
                setParameter("id",customerId).
                setParameter("start",start).
                setParameter("end",end).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<CustomerPassbook> getCustomerPassbookbyByDate(long customerId, LocalDate date) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from CustomerPassbook where date=:date and customerid=:id";
            return session.createQuery(hql,CustomerPassbook.class).
                    setParameter("date",date).
                    setParameter("id",customerId).list();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int saveCustomerPassbook(CustomerPassbook book) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            if(book.getId()==0)
            {
                session.save(book);
                session.getTransaction().commit();
                return 1;
            }
            else
            {
                session.update(book);
                session.getTransaction().commit();
                return 2;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}