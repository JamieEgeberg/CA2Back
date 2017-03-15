/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Util;

/**
 *
 * @author Jamie
 */
public class PersonFacadeTest {

    EntityManagerFactory emf;
    PersonFacade instance;

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Persistence.generateSchema("testPU", null);
        emf = Persistence.createEntityManagerFactory("testPU");
        instance = new PersonFacade();
        Util.databaseInit(emf);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addEntityManagerFactory method, of class PersonFacade.
     */
    @Test
    public void testAddEntityManagerFactory() {
        System.out.println("addEntityManagerFactory");
        assertEquals(instance.emf, null);
        instance.addEntityManagerFactory(emf);
        assertEquals(instance.emf, emf);
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson_long() {
        System.out.println("getPerson");
        instance.addEntityManagerFactory(emf);
        long id = 1L;
        Person result = instance.getPerson(id);
        assertTrue(id == result.getId());
    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_0args() {
        System.out.println("getPersons");
        instance.addEntityManagerFactory(emf);
        List<Person> result = instance.getPersons();
        result.forEach((res)
                -> assertTrue(res instanceof Person));
    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_String() {
        System.out.println("getPersons");
        instance.addEntityManagerFactory(emf);
        String zipCode = "3600";
        List<Person> result = instance.getPersons(zipCode);
        result.forEach((res)
                -> assertEquals(zipCode, res.getAddress().getCity().getZipCode()));
    }

    /**
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        instance.addEntityManagerFactory(emf);
        Person p = new Person("Test", "Testsen", "test@test.dk");
        Person result = instance.addPerson(p);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getFirstName(), result.getFirstName());
        assertEquals(p.getLastName(), result.getLastName());
    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        instance.addEntityManagerFactory(emf);
        Person expResult = instance.addPerson(new Person("Test", "Testsen", "test@test.dk"));
        long id = expResult.getId();
        Person result = instance.deletePerson(id);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getFirstName(), result.getFirstName());
        assertEquals(expResult.getLastName(), result.getLastName());
    }

    /**
     * Test of editPerson method, of class PersonFacade.
     */
    @Test
    public void testEditPerson() {
        System.out.println("editPerson");
        instance.addEntityManagerFactory(emf);
        Person p = instance.addPerson(new Person("Test", "Testsen", "test@test.dk"));
        p.setFirstName("TestNavn");
        Person result = instance.editPerson(p);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getFirstName(), result.getFirstName());
        assertEquals(p.getLastName(), result.getLastName());
    }

}
