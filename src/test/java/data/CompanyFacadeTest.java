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
        assert(id==result.getId());
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
        assertEquals(3, result.size());
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_String() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        String zipCode = "";
        List<Company> expResult = null;
        List<Company> result = instance.getCompanies(zipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_int() {
        System.out.println("getCompanies");
        instance.addEntityManagerFactory(emf);
        int employees = 0;
        List<Company> expResult = null;
        List<Company> result = instance.getCompanies(employees);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompany method, of class CompanyFacade.
     */
    @Test
    public void testAddCompany() {
        System.out.println("addCompany");
        instance.addEntityManagerFactory(emf);
        Company c = null;
        Company expResult = null;
        Company result = instance.addCompany(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCompany method, of class CompanyFacade.
     */
    @Test
    public void testEditCompany() {
        System.out.println("editCompany");
        instance.addEntityManagerFactory(emf);
        Company c = null;
        Company expResult = null;
        Company result = instance.editCompany(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompany method, of class CompanyFacade.
     */
    @Test
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        instance.addEntityManagerFactory(emf);
        int id = 0;
        Company expResult = null;
        Company result = instance.deleteCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
