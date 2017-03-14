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

    void addEntityManagerFactory(EntityManagerFactory emf);

    Person getPerson(int id);

    Person getPerson(long id);

    List<Person> getPersons();

    List<Person> getPersons(String zipCode);

    Person addPerson(Person p);

    Person deletePerson(long id);

    Person editPerson(Person p);

}
