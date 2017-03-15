/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jamie
 */
public class CompanyFacadeTest {
    
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
        EntityManagerFactory emf = null;
        CompanyFacade instance = new CompanyFacade();
        instance.addEntityManagerFactory(emf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCompany_int() {
        System.out.println("getCompany");
        int id = 0;
        CompanyFacade instance = new CompanyFacade();
        Company expResult = null;
        Company result = instance.getCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCompany_long() {
        System.out.println("getCompany");
        long id = 0L;
        CompanyFacade instance = new CompanyFacade();
        Company expResult = null;
        Company result = instance.getCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCompany_String() {
        System.out.println("getCompany");
        String cvr = "";
        CompanyFacade instance = new CompanyFacade();
        Company expResult = null;
        Company result = instance.getCompany(cvr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_0args() {
        System.out.println("getCompanies");
        CompanyFacade instance = new CompanyFacade();
        List<Company> expResult = null;
        List<Company> result = instance.getCompanies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCompanies_String() {
        System.out.println("getCompanies");
        String zipCode = "";
        CompanyFacade instance = new CompanyFacade();
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
        int employees = 0;
        CompanyFacade instance = new CompanyFacade();
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
        Company c = null;
        CompanyFacade instance = new CompanyFacade();
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
        Company c = null;
        CompanyFacade instance = new CompanyFacade();
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
        int id = 0;
        CompanyFacade instance = new CompanyFacade();
        Company expResult = null;
        Company result = instance.deleteCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
