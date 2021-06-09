package hibernate.dao.daoimpl;

import hibernate.dao.dao.CustomerDao;
import hibernate.entities.Customer;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> getAllCustomer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from customer";
            return session.createQuery(hql, Customer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getAllCustomerNames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "select CONCAT(fname,' ',mname,' ',lname) from Customer order by fname";
            return session.createQuery(hql, String.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer getCustomerById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer findByCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Customer where code=:code";
            return session.createQuery(hql, Customer.class).setParameter("code", code).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer findByContact(String contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "From Customer where contact=:contact";
            return session.createQuery(hql, Customer.class).setParameter("contact", contact).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer findByFullName(String fname, String mname, String lname) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Customer where fname=:fname and mname=:mname and lname=:lname";
            return session.createQuery(hql, Customer.class).
                    setParameter("fname", fname).
                    setParameter("mname", mname).
                    setParameter("lname", lname).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int saveCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (customer.getId() == 0) {
                session.save(customer);
                session.getTransaction().commit();
                return 1;

            } else
            {
                session.update(customer);
                session.getTransaction().commit();
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCustomer(Customer customer) {
    return saveCustomer(customer);
    }
}
