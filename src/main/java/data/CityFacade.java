package data;

import entity.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public class CityFacade implements ICityFacade {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("PU");

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
     */
    @Override
    public City getCity(int id) {
        return getCity(((long) id));
    }

    /**
     * Get a single city by it's Identity id
     * @param id Identity id
     * @return single city by id
     */
    @Override
    public City getCity(long id) {
        return find(id);
    }

    /**
     * Get a single city by it's zip code
     * @param zipCode zip code
     * @return single city by zip code
     */
    @Override
    public City getCity(String zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE" +
                                                        " c.zipCode = :zipCode",
                                                City.class);
        query.setParameter("zipCode", zipCode);
        return query.getSingleResult();
    }

    /**
     * Get all cities
     * @return all cities
     */
    @Override
    public List<City> getCities() {
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
    City find(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(City.class, id);
    }

}
