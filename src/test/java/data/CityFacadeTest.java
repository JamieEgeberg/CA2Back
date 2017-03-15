package data;

import entity.City;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Niki on 2017-03-15.
 *
 * @author Niki
 */
public class CityFacadeTest {

    private EntityManagerFactory emf;
    private CityFacade instance;

    @BeforeClass
    public static void setUpClass() {
        Persistence.generateSchema("testPU", null);
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testPU");
        instance = new CityFacade();
        instance.addEntityManagerFactory(emf);
    }

    /**
     * Test of addEntityManagerFactory method, of class CompanyFacade.
     */
    @Test
    public void testAddEntityManagerFactory() {
        System.out.println("addEntityManagerFactory");
        instance.addEntityManagerFactory(null);
        assertEquals(instance.emf, null);
        instance.addEntityManagerFactory(emf);
        assertEquals(instance.emf, emf);
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCity_long() {
        System.out.println("getCity");
        long id = 6L;
        City result = instance.getCity(id);
        assertTrue(id == result.getId());
    }

    /**
     * Test of getCompany method, of class CompanyFacade.
     */
    @Test
    public void testGetCityByZipCode_String() {
        System.out.println("getCity");
        String zipCode = "0800";
        City result = instance.getCity(zipCode);
        assertEquals(zipCode, result.getZipCode());
    }

    /**
     * Test of getCompanies method, of class CompanyFacade.
     */
    @Test
    public void testGetCities_0args() {
        System.out.println("getCities");
        List<City> result = instance.getCities();
        assertTrue(result.size() > 0);
        result.forEach((res) -> assertTrue(res != null));
    }

}
