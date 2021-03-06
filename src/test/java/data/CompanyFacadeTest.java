/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import exception.TheException;
import org.junit.*;
import util.Util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author Jamie
 */
public class CompanyFacadeTest {

    private EntityManagerFactory emf;
    private CompanyFacade instance;

    @Before
    public void setUp() throws TheException {
        Persistence.generateSchema("testPU", null);
        emf = Persistence.createEntityManagerFactory("testPU");
        instance = new CompanyFacade();
        Util.databaseInit(emf);
    }

    /**
     * Test of addEntityManagerFactory method, of class CompanyFacade.
     */
    @Test
    public void testAddEntityManagerFactory() {
        System.out.println("addEntityManagerFactory");
        assertEquals(instance.emf, null);
        instance.addEntityManagerFactory(emf);
        assertEquals(instance.emf, emf);
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCompany_long() {
        System.out.println("getCompany");
        instance.addEntityManagerFactory(emf);
        long id = 6L;
        Company result = null;
        try {
            result = instance.getCompany(id);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertTrue(id == result.getId());
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCompany_String() {
        System.out.println("getCompany");
        instance.addEntityManagerFactory(emf);
        String cvr = "36363636";
        Company result = null;
        try {
            result = instance.getCompany(cvr);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertEquals(cvr, result.getCvr());
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_0args() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        List<Company> result = null;
        try {
            result = instance.getCompanies();
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertTrue(result.size() > 0);
        result.forEach((res)
                -> assertTrue(res != null));
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_String() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        String zipCode = "3600";
        List<Company> result = null;
        try {
            result = instance.getCompanies(zipCode);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        result.forEach((res)
                -> assertEquals(zipCode, res.getAddress().getCity().getZipCode()));
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_int() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        int employees = 30;
        List<Company> result = null;
        try {
            result = instance.getCompanies(employees);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        result.forEach((res)
                -> assertTrue(employees < res.getNoOfEmployees()));
    }

    /**
     * Test of addCompany method, of class CompanyFacade.
     */
    @Test
    public void testAddCompany() {
        System.out.println("addCompany");
        instance.addEntityManagerFactory(emf);
        Company c = new Company("Test Co", "Testes", "00000000", 10, 10000, "test@test.dk");
        Company result = null;
        try {
            result = instance.addCompany(c);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertEquals(c.getId(), result.getId());
        assertEquals(c.getName(), result.getName());
        assertEquals(c.getCvr(), result.getCvr());
    }

    /**
     * Test of editCompany method, of class CompanyFacade.
     */
    @Test
    public void testEditCompany() {

        System.out.println("editCompany");
        instance.addEntityManagerFactory(emf);
        Company c = new Company("Test Co", "Testes", "00000000", 10, 10000, "test@test.dk");
        Company result = null;
        try {
            instance.addCompany(c);
            c.setCvr("99999999");
            result = instance.editCompany(c);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertEquals(c.getId(), result.getId());
        assertEquals(c.getName(), result.getName());
        assertEquals(c.getCvr(), result.getCvr());
    }

    /**
     * Test of deleteCompany method, of class CompanyFacade.
     */
    @Test
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        instance.addEntityManagerFactory(emf);
        Company expResult = new Company("Test Co", "Testes", "00000000", 10, 10000, "test@test.dk");
        Company result = null;
        try {
            instance.addCompany(expResult);
            long id = expResult.getId();
            result = instance.deleteCompany(id);
        } catch (TheException ex) {
            fail(ex.getMessage());
        }
        assertNotNull(result);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getCvr(), result.getCvr());
    }

}
