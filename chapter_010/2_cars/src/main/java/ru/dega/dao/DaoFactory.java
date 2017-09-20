package ru.dega.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * DaoFactory class.
 *
 * @author Denis
 * @since 11.09.2017
 */
public class DaoFactory {
    /**
     * Session factory.
     */
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Session factory not initialized");
        }
    }

    /**
     * Default constructor.
     */
    public DaoFactory() {
    }

    /**
     * Get hibernate session factory.
     *
     * @return session factory
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Close session factory.
     */
    public static void closeSessionFactory() {
        SESSION_FACTORY.close();
    }
}