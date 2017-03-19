/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Person;
import exception.TheException;
import org.junit.*;
import util.Util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jamie
 */
public class PersonFacadeTest {

    private EntityManagerFactory emf;
    private PersonFacade instance;

    @Before
    public void setUp() throws TheException {
        Persistence.generateSchema("testPU", null);
        emf = Persistence.createEntityManagerFactory("testPU");
        instance = new PersonFacade();
        Util.databaseInit(emf);
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
        Person result = null;
        try {
            result = instance.getPerson(id);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertTrue(id == result.getId());
    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_0args() {
        System.out.println("getPersons");
        instance.addEntityManagerFactory(emf);
        List<Person> result = null;
        try {
            result = instance.getPersonsByZipCode();
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertTrue(result.size() > 0);
        result.forEach((res) -> assertTrue(res != null));
    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_String() {
        System.out.println("getPersons");
        instance.addEntityManagerFactory(emf);
        String zipCode = "3600";
        List<Person> result = null;
        try {
            result = instance.getPersonsByZipCode(zipCode);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        result.forEach((res)
                               -> assertEquals(zipCode, res.getAddress()
                                                           .getCity()
                                                           .getZipCode()));
    }

    /**
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        instance.addEntityManagerFactory(emf);
        Person p = new Person("Test", "Testsen", "test@test.dk");
        Person result = null;
        try {
            result = instance.addPerson(p);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
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
        Person expResult = new Person("Test", "Testsen", "test@test.dk");
        Person result = null;
        try {
            instance.addPerson(expResult);
            long id = expResult.getId();
            result = instance.deletePerson(id);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
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
        Person p = new Person("Test", "Testsen", "test@test.dk");
        Person result = null;
        try {
            instance.addPerson(p);
            p.setFirstName("TestNavn");
            result = instance.editPerson(p);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getFirstName(), result.getFirstName());
        assertEquals(p.getLastName(), result.getLastName());
    }

}
