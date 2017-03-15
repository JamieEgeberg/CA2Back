package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class Utility {

    public static void persist(EntityManagerFactory emf, Object o) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } catch (PersistenceException ignored) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void merge(EntityManagerFactory emf, Object o) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

}
