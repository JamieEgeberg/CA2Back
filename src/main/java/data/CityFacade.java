package data;

import entity.City;
import exception.TheException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class CityFacade implements ICityFacade {

    EntityManagerFactory emf = null;

    /**
     * Add an Entity Manager Factory which will be used for
     * when generating Entity Managers for the other methods
     * @param emf
     */
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Get a single city by it's Identity id
     * @param id Identity id
     * @return single city by id
     * @throws exception.TheException
     */
    @Override
    public City getCity(int id) throws TheException {
        return getCity(((long) id));
    }

    /**
     * Get a single city by it's Identity id
     * @param id Identity id
     * @return single city by id
     * @throws exception.TheException
     */
    @Override
    public City getCity(long id) throws TheException {
        return find(id);
    }

    /**
     * Get a single city by it's zip code
     * @param zipCode zip code
     * @return single city by zip code
     * @throws exception.TheException
     */
    @Override
    public City getCity(String zipCode) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE" +
                                                        " c.zipCode = :zipCode",
                                                City.class);
        query.setParameter("zipCode", zipCode);
        List<City> list = query.getResultList();
        if(list.size() > 0) return list.get(0);
        else throw new TheException("No city with the zipCode: " + zipCode);
    }

    /**
     * Get all cities
     * @return all cities
     * @throws exception.TheException
     */
    @Override
    public List<City> getCities() throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c",
                                                  City.class);
        return query.getResultList();
    }

    /**
     * Find City by Id
     * @param id Identity Id
     * @return City with given Id
     */
    City find(Long id) throws TheException {
        EntityManager em = emf.createEntityManager();

        City c = em.find(City.class, id);
        if (c == null)
            throw new TheException("Cannot find Person with id " + id);
        return c;
    }

}
