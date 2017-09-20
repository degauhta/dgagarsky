package ru.dega.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.dega.dao.DaoFactory;
import ru.dega.dao.UserDao;
import ru.dega.models.User;

import java.util.List;

/**
 * UserHibernateDao class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class UserHibernateDao implements UserDao {
    /**
     * Get user by login.
     *
     * @param login login
     * @return found user
     */
    @Override
    public User getUserByLogin(String login) {
        User result = null;
        try (Session session = DaoFactory.getSessionFactory().openSession();) {
            session.beginTransaction();
            Query query = session.createQuery(
                    "select u from User u where u.login like :login")
                    .setParameter("login", login);
            @SuppressWarnings("unchecked")
            List<User> list = query.getResultList();
            if (list.size() != 0) {
                result = list.get(0);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }
}