package data;

import entity.Person;
import util.Utility;

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

    /**
     * Add an Entity Manager Factory which will be used for
     * when generating Entity Managers for the other methods
     * @param emf
     */
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Get a single Person by it's Identity id
     * @param id Identity id
     * @return single Person by id
     */
    @Override
    public Person getPerson(int id) {
        return getPerson((long) id);
    }

    /**
     * Get a single Person by it's Identity id
     * @param id Identity id
     * @return single Person by id
     */
    @Override
    public Person getPerson(long id) {
        return find(id);
    }

    /**
     * Get all persons
     * @return all persons
     */
    @Override
    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",
                                                  Person.class);
        return query.getResultList();
    }

    /**
     * Get all persons in given zip code
     * @param zipCode zip code
     * @return all persons with given zip code
     */
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

    /**
     * Add person to database and return it
     * @param p person
     * @return person added
     */
    @Override
    public Person addPerson(Person p) {
        Utility.persist(emf, p);
        return p;
    }

    /**
     * Delete person from database with given Id
     * @param id Identity Id
     * @return Person deleted
     */
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

    /**
     * Update a person in the database
     * @param p person
     * @return person updated
     */
    @Override
    public Person editPerson(Person p) {
        Utility.merge(emf, p);
        return p;
    }

    /**
     * Find Person by Id
     * @param id Identity Id
     * @return Person with given Id
     */
    Person find(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }

}
