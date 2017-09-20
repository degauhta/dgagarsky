package ru.dega.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.dega.dao.AdvertisementDao;
import ru.dega.dao.DaoFactory;
import ru.dega.models.Advertisement;

import java.util.List;

/**
 * AdvertisementHibernateDao class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class AdvertisementHibernateDao implements AdvertisementDao {
    /**
     * Create new advert.
     *
     * @param advertisement advertisement
     */
    @Override
    public void createAdvertisement(Advertisement advertisement) {
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(advertisement);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all advert.
     *
     * @return list of advert
     */
    @Override
    public List<Advertisement> getAllAdvertisement() {
        List<Advertisement> result = null;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            //noinspection unchecked
            result = session.createQuery("from Advertisement order by id").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get all not sold advert.
     *
     * @return list of advert
     */
    @Override
    public List<Advertisement> getAllNotSoldAdvertisement() {
        List<Advertisement> result = null;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            //noinspection unchecked
            result = session.createQuery("from Advertisement where sold = false order by id").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Set sold status.
     *
     * @param id advert id
     * @return true if status set successfully
     */
    @Override
    public boolean updateSoldStatus(int id) {
        boolean result = true;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("update Advertisement set sold = true where id = :advertId")
                    .setParameter("advertId", id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}