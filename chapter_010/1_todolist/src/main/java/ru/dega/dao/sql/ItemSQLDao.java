package ru.dega.dao.sql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.dega.dao.DaoFactory;
import ru.dega.dao.ItemDao;
import ru.dega.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemSQLDao class.
 *
 * @author Denis
 * @since 11.09.2017
 */
public class ItemSQLDao implements ItemDao {
    /**
     * Create to-do item.
     *
     * @param item item
     * @return true if create successfully
     */
    @Override
    public boolean createItem(Item item) {
        boolean result = true;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update to-do item.
     *
     * @param item updated item
     * @return true if update successfully
     */
    @Override
    public boolean updateItem(Item item) {
        boolean result = true;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete to-do item by id.
     *
     * @param item item
     * @return true if create successfully
     */
    @Override
    public boolean deleteItem(Item item) {
        boolean result = true;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get all to-do items.
     *
     * @return list of items
     */
    @Override
    public List<Item> getAllItems() {
        long s = System.currentTimeMillis();
        List<Item> itemList = new ArrayList<>();
        Session session = DaoFactory.getSessionFactory().openSession();
        System.out.println("Create factory = " + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        session.beginTransaction();
        //noinspection unchecked
        itemList = session.createQuery("from Item").list();
        session.close();
        System.out.println("Work with session = " + (System.currentTimeMillis() - s));
        return itemList;
    }

    /**
     * Get to-do items with status not done.
     *
     * @return list of items
     */
    @Override
    public List<Item> getNotDoneItems() {
        List<Item> itemList;
        Session session = DaoFactory.getSessionFactory().openSession();
        session.beginTransaction();
        //noinspection unchecked
        itemList = session.createQuery("from Item where done = false").list();
        session.close();
        return itemList;
    }
}