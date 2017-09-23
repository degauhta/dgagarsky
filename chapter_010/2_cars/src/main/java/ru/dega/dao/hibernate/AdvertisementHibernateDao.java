package ru.dega.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.dega.dao.AdvertisementDao;
import ru.dega.dao.DaoFactory;
import ru.dega.models.Advertisement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    /**
     * Get filtered avert.
     *
     * @param filters filters
     * @return list of advert
     */
    @Override
    public List<Advertisement> getFilteredAdvertisement(String[] filters) {
        List<Advertisement> result = null;
        try (Session session = DaoFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Advertisement> criteriaQuery = builder.createQuery(Advertisement.class);
            Root<Advertisement> advertRoot = criteriaQuery.from(Advertisement.class);
            criteriaQuery.select(advertRoot);
            if (filters[0].length() > 0) {
                criteriaQuery.where(builder.like(advertRoot.get("description"),
                        String.format("%%%s%%", filters[0])));
            }
            if (filters[1].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carBrand").get("name")),
                        String.format("%%%s%%", filters[1])));
            }
            if (filters[2].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carModel").get("name")),
                        String.format("%%%s%%", filters[2])));
            }
            if (filters[3].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carBody").get("name")),
                        String.format("%%%s%%", filters[3])));
            }
            if (filters[4].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carTransmission").get("name")),
                        String.format("%%%s%%", filters[4])));
            }
            if (filters[5].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carEngine").get("name")),
                        String.format("%%%s%%", filters[5])));
            }
            if (filters[6].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("carDriveType").get("name")),
                        String.format("%%%s%%", filters[6])));
            }
            if (filters[7].length() > 0) {
                criteriaQuery.where(builder.like(builder.lower(advertRoot.get("author").get("login")),
                        String.format("%%%s%%", filters[7])));
            }
            criteriaQuery.orderBy(builder.asc(advertRoot.get("id")));
            result = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }
}