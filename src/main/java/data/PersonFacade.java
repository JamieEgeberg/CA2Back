package data;

import entity.Person;
import exception.TheException;
import util.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
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
     * @throws exception.TheException
     */
    @Override
    public Person getPerson(int id) throws TheException {
        return getPerson((long) id);
    }

    /**
     * Get a single Persons contact info by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     * @throws exception.TheException
     */
    @Override
    public Person getPersonContactInfo(int id) throws TheException {
        Person p = getPerson((long) id);
        p.hobbies = null;
        return p;
    }

    /**
     * Get a single Person by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     * @throws exception.TheException
     */
    @Override
    public Person getPerson(long id) throws TheException {
        return find(id);
    }

    /**
     * Get all persons
     *
     * @return all persons
     * @throws exception.TheException
     */
    @Override
    public List<Person> getPersonsByZipCode() throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",
                                                  Person.class);
        List<Person> l = query.getResultList();
        if (l.isEmpty()) {
            throw new TheException("No persons found");
        }
        return l;
    }

    /**
     * Get all persons in given zip code
     *
     * @param zipCode zip code
     * @return all persons with given zip code
     * @throws exception.TheException
     */
    @Override
    public List<Person> getPersonsByZipCode(String zipCode) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p "
                                                          + "WHERE p.address"
                                                          + ".city.zipCode = "
                                                          + ":zipCode",
                                                  Person.class);
        query.setParameter("zipCode", zipCode);
        List<Person> l = query.getResultList();
        if (l == null) {
            throw new TheException("No persons in the zipCode:" + zipCode);
        }
        return l;
    }

    /**
     * Get all persons with the given hobby
     *
     * @param id hobby id
     * @return all persons with the given hobby
     * @throws exception.TheException
     */
    @Override
    public List<Person> getPersonsByHobby(long id) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p " +
                                                          "INNER JOIN " +
                                                          "p.hobbies h " +
                                                          "WHERE " +
                                                          "h.id = :hobby",
                                                  Person.class);
        query.setParameter("hobby", id);
        List<Person> l = query.getResultList();
        if (l == null) {
            throw new TheException("No persons with the hobby: " + id);
        }
        return l;
    }

    /**
     * Get all persons contact info
     *
     * @return all persons
     * @throws exception.TheException
     */
    @Override
    public List<Person> getPersonsContactInfo() throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p ",
                                                  Person.class);
        List<Person> l = query.getResultList();
        if (l == null) {
            throw new TheException("No persons found");
        }
        l.forEach((p) -> p.hobbies = null);
        return l;
    }

    /**
     * Add person to database and return it
     *
     * @param p person
     * @return person added
     * @throws exception.TheException
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
     * @throws exception.TheException
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
                    "Could not delete. Rollback, object: " + (toBeRemoved !=
                            null ? toBeRemoved
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
     * @throws exception.TheException
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
    private Person find(Long id) throws TheException {
        EntityManager em = emf.createEntityManager();

        Person p = em.find(Person.class, id);
        if (p == null) {
            throw new TheException("Person with id: " + id + " not found.");
        }
        return p;
    }

}
