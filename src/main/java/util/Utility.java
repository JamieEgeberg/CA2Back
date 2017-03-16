package util;

import exception.TheException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class Utility {

    public static void persist(EntityManagerFactory emf, Object o) throws
            TheException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } catch (PersistenceException ignored) {
            em.getTransaction().rollback();
            throw new TheException("Persist failed rollback, object: " + o
                    .toString());
        } finally {
            em.close();
        }
    }

    public static void merge(EntityManagerFactory emf, Object o) throws
            TheException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new TheException("Merge failed rollback, object: " + o
                    .toString());
        }
    }

}
