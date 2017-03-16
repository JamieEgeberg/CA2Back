package data;

import entity.Person;
import exception.TheException;
import util.Utility;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf = null;

    /**
     * Add an Entity Manager Factory which will be used for when generating
     * Entity Managers for the other methods
     *
     * @param emf
     */
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Get a single Person by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     */
    @Override
    public Person getPerson(int id) throws TheException {
        return getPerson((long) id);
    }

    /**
     * Get a single Person by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     */
    @Override
    public Person getPerson(long id) throws TheException {
        return find(id);
    }

    /**
     * Get all persons
     *
     * @return all persons
     */
    @Override
    public List<Person> getPersons() throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",
                                                  Person.class);
        List<Person> l = query.getResultList();
        if (l == null) throw new TheException("No persons sexist");
        return l;
    }

    /**
     * Get all persons in given zip code
     *
     * @param zipCode zip code
     * @return all persons with given zip code
     */
    @Override
    public List<Person> getPersons(String zipCode) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p "
                                                          + "WHERE p.address"
                                                          + ".city.zipCode = "
                                                          + ":zipCode",
                                                  Person.class);
        query.setParameter("zipCode", zipCode);
        List<Person> l = query.getResultList();
        if (l == null)
            throw new TheException("No persons in the zipCode:" + zipCode);
        return l;
    }

    /**
     * Add person to database and return it
     *
     * @param p person
     * @return person added
     */
    @Override
    public Person addPerson(Person p) throws TheException {
        Utility.persist(emf, p);
        return p;
    }

    /**
     * Delete person from database with given Id
     *
     * @param id Identity Id
     * @return Person deleted
     */
    @Override
    public Person deletePerson(long id) throws TheException {
        EntityManager em = emf.createEntityManager();
        Person toBeRemoved = null;
        try {
            em.getTransaction().begin();
            toBeRemoved = em.merge(find(id));
            em.remove(toBeRemoved);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new TheException(
                    "rollback, object: " + (toBeRemoved != null ? toBeRemoved
                            .toString() : "null"));
        } finally {
            em.close();
        }
        return toBeRemoved;
    }

    /**
     * Update a person in the database
     *
     * @param p person
     * @return person updated
     */
    @Override
    public Person editPerson(Person p) throws TheException {
        Utility.merge(emf, p);
        return p;
    }

    /**
     * Find Person by Id
     *
     * @param id Identity Id
     * @return Person with given Id
     */
    Person find(Long id) throws TheException {
        EntityManager em = emf.createEntityManager();

        Person p = em.find(Person.class, id);
        if (p == null)
            throw new TheException("Cannot find Person with id " + id);
        return p;
    }

}
