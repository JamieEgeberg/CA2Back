package data;

import entity.Person;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("PU");

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person getPerson(int id) {
        return getPerson((long) id);
    }

    @Override
    public Person getPerson(long id) {
        return find(id);
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",
                                                  Person.class);
        return query.getResultList();
    }

    @Override
    public List<Person> getPersons(String zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p " +
                                                          "WHERE p.address" +
                                                          ".city.zipCode = " +
                                                          ":zipCode",
                                                  Person.class);
        query.setParameter("zipCode", zipCode);
        return query.getResultList();
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (PersistenceException ignored) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person deletePerson(long id) {
        EntityManager em = emf.createEntityManager();
        Person toBeRemoved = null;
        try {
            em.getTransaction().begin();
            toBeRemoved = em.merge(find(id));
            em.remove(toBeRemoved);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return toBeRemoved;
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
        return p;
    }

    Person find(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }

}
