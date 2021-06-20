package hibernate.dao.daoimpl;

import hibernate.dao.dao.BillDao;
import hibernate.entities.Bill;
import hibernate.entities.Transaction;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class BillDaoImpl implements BillDao {

    @Override
    public List<Bill> getAllBills() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Bill";
            return session.createQuery(hql, Bill.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Bill getBillByBillno(Long billno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.get(Bill.class, billno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Bill> getBillByDate(LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Bill where date=:date";
            return session.createQuery(hql, Bill.class).setParameter("date", date).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Bill> getBillByCustomer(long customerid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Bill where customerid=:cid";
            return session.createQuery(hql, Bill.class).setParameter("cid", customerid).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Bill> getBillByLogin(int loginid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Bill where loginid=:lid";
            return session.createQuery(hql, Bill.class).setParameter("lid", loginid).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Bill> getDatePeriodBill(LocalDate start, LocalDate end) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Bill where date BETWEEN :startdate AND :enddate";
            return session.createQuery(hql, Bill.class).setParameter("startdate", start).
                    setParameter("enddate", end).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int saveBill(Bill bill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (bill.getBillno() == 0) {
                 session.save(bill);
                session.getTransaction().commit();
                return 1;
            } else {
                if (deleteTransactionByBillno(bill.getBillno()) == 1) {
                     session.update(bill);
                    session.getTransaction().commit();
                    return 2;
                } else
                    return 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int deleteTransactionByBillno(long billno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            for (Transaction tr : getBillByBillno(billno).getTransaction()) {
                session.delete(tr);
            }
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return  0;
        }
    }

    @Override
    public int updateBill(Bill bill) {
        return saveBill(bill);
    }
}
