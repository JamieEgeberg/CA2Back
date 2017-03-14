package data;

import entity.Person;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public interface IPersonFacade {

    /**
     * Add an Entity Manager Factory which will be used for
     * when generating Entity Managers for the other methods
     *
     * @param emf
     */
    void addEntityManagerFactory(EntityManagerFactory emf);

    /**
     * Get a single Person by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     */
    Person getPerson(int id);

    /**
     * Get a single Person by it's Identity id
     *
     * @param id Identity id
     * @return single Person by id
     */
    Person getPerson(long id);

    /**
     * Get all persons
     *
     * @return all persons
     */
    List<Person> getPersons();

    /**
     * Get all persons in given zip code
     *
     * @param zipCode zip code
     * @return all persons with given zip code
     */
    List<Person> getPersons(String zipCode);

    /**
     * Add person to database and return it
     *
     * @param p person
     * @return person added
     */
    Person addPerson(Person p);

    /**
     * Delete person from database with given Id
     *
     * @param id Identity Id
     * @return Person deleted
     */
    Person deletePerson(long id);

    /**
     * Update a person in the database
     *
     * @param p person
     * @return person updated
     */
    Person editPerson(Person p);

}
