/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.Util;
import static org.junit.Assert.*;

/**
 *
 * @author Jamie
 */
public class CompanyFacadeTest {

    EntityManagerFactory emf;
    CompanyFacade instance;

    public CompanyFacadeTest() {
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
        instance = new CompanyFacade();
        Util.databaseInit(emf);
    }

    @After
    public void tearDown() {
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
        Company result = instance.getCompany(id);
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
        Company result = instance.getCompany(cvr);
        assertEquals(cvr, result.getCvr());
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_0args() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        List<Company> result = instance.getCompanies();
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
        List<Company> result = instance.getCompanies(zipCode);
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
        List<Company> result = instance.getCompanies(employees);
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
        Company result = instance.addCompany(c);
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
        instance.addCompany(new Company("Test Co", "Testes", "00000000", 10, 10000, "test@test.dk"));
        Company c = instance.getCompany("00000000");
        c.setCvr("99999999");
        Company result = instance.editCompany(c);

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
        instance.addCompany(new Company("Test Co", "Testes", "00000000", 10, 10000, "test@test.dk"));
        Company expResult = instance.getCompany("00000000");
        long id = expResult.getId();
        Company result = instance.deleteCompany(id);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getCvr(), result.getCvr());
    }

}
