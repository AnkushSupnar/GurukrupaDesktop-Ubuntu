package hibernate.dao.daoimpl;

import hibernate.dao.dao.LoginDao;
import hibernate.entities.Login;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
    @Override
    public List<Login> getAllLogin() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Login";
            return session.createQuery(hql, Login.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Login getLoginByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Login where username=:uname";
            return session.createQuery(hql, Login.class).setParameter("uname", name).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Login getLoginByPerson(String person) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Login where person=:person";
            return session.createQuery(hql, Login.class).setParameter("person", person).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Login getLoginById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.get(Login.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int validateLogin(String name, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Login login = getLoginByName(name);
            if (login != null) {
                if (login.getPassword().equals(password))
                    return 1;
                else
                    return 0;
            } else
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int saveLogin(Login login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if(login.getId()==0)
            {
                session.save(login);
                session.getTransaction().commit();
                return 1;
            }
            else
            {
                session.update(login);
                session.getTransaction().commit();
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> getAllUserNames() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "select userName from Login";
            return session.createQuery(hql,String.class).list();
        }
    }
}
