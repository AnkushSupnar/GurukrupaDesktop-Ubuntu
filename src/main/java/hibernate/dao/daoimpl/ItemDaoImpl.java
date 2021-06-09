package hibernate.dao.daoimpl;

import hibernate.dao.dao.ItemDao;
import hibernate.entities.Item;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public List<Item> getAllItems() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from item";
            return session.createQuery(hql,Item.class).list();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Item getItemById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            return session.get(Item.class,id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Item getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="from item where itemname=:name";
            return session.createQuery(hql,Item.class).setParameter("name",name).getSingleResult();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<String> getAllItemNames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql="itemname from item";
            return session.createQuery(hql,String.class).list();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int saveItem(Item item) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            if(item.getId()==0)
            {
                session.save(item);
                session.getTransaction().commit();
                return 1;
            }
            else {
                session.update(item);
                session.getTransaction().commit();
                return 2;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int updateItem(Item item) {
        return saveItem(item);
    }
}
