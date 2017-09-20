package ru.dega.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.dega.dao.AbstractDao;
import ru.dega.dao.DaoFactory;

import java.util.List;

/**
 * AbstractHibernateDao class.
 *
 * @param <E> the type of elements
 * @author Denis
 * @since 17.09.2017
 */
public class AbstractHibernateDao<E> implements AbstractDao<E> {
    /**
     * Get all abstract items.
     *
     * @param Query hql string
     * @return list
     */
    @Override
    public List<E> getAll(String query) {
        List<E> result = null;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            //noinspection unchecked
            result = session.createQuery(query).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }
}