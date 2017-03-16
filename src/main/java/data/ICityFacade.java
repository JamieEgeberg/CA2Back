package data;

import entity.City;
import exception.TheException;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public interface ICityFacade {

    /**
     * Add an Entity Manager Factory which will be used for
     * when generating Entity Managers for the other methods
     *
     * @param emf
     */
    void addEntityManagerFactory(EntityManagerFactory emf);

    /**
     * Get a single city by it's Identity id
     *
     * @param id Identity id
     * @return single city by id
     */
    City getCity(int id) throws TheException;

    /**
     * Get a single city by it's Identity id
     *
     * @param id Identity id
     * @return single city by id
     */
    City getCity(long id) throws TheException;

    /**
     * Get a single city by it's zip code
     *
     * @param zipCode zip code
     * @return single city by zip code
     */
    City getCity(String zipCode) throws TheException;

    /**
     * Get all cities
     *
     * @return all cities
     */
    List<City> getCities() throws TheException;

}
