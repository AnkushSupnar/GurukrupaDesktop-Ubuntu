package hibernate.dao.daoimpl;

import hibernate.dao.dao.BankDao;
import hibernate.entities.Bank;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BankDaoImpl implements BankDao {
    @Override
    public Bank getBankById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Bank bank = session.get(Bank.class,id);
            return bank;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Bank> getAllBanks() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from bank";
        List<Bank>list = session.createQuery(hql,Bank.class).list();
        return list;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Bank getBankByBankName(String bankname) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "from bank where bankname=:name";
            Bank bank = session.createQuery(hql,Bank.class).setParameter("name",bankname).getSingleResult();
            return bank;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<String> getAllBankNames() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "bankname from bank";
            List<String>list = session.createQuery(hql,String.class).list();
            return list;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public double getBankBalance(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "select balance from bank where id=:id";
            return session.createQuery(hql,Double.class).setParameter("id",id).uniqueResult();
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int addBankBalance(int id, double balance) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Bank bank = session.get(Bank.class,id);
            if(bank!=null)
            {
                bank.setBalance(bank.getBalance()+balance);
                session.update(bank);
                session.getTransaction().commit();
                return 1;
            }
            else return 0;

        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int reduceBankBalance(int id, double balance) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Bank bank = getBankById(id);
            if(bank!=null)
            {
                bank.setBalance(bank.getBalance()-balance);
                session.update(bank);
                session.getTransaction().commit();
                return 1;
            }
            else
                return 0;
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int saveBank(Bank bank) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            if(bank.getId()==0) {
                 session.save(bank);
                 session.getTransaction().commit();
                 return 1;
            }
            else if(getBankById(bank.getId())!=null){
                session.update(bank);
                session.getTransaction().commit();
                return 2;
            }
            else return 0;
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int updateBank(Bank bank) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            if(getBankById(bank.getId())!=null)
            {
                session.update(bank);
                session.getTransaction().commit();
                return 1;
            }
            else
                return 0;
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
