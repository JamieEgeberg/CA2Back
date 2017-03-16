package data;

import entity.Company;
import exception.TheException;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public interface ICompanyFacade {

    /**
     * Add an Entity Manager Factory which will be used for
     * when generating Entity Managers for the other methods
     *
     * @param emf
     */
    void addEntityManagerFactory(EntityManagerFactory emf);

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     * @throws exception.TheException
     */
    Company getCompany(int id) throws TheException;

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     * @throws exception.TheException
     */
    Company getCompany(long id) throws TheException;

    /**
     * Get a single company by it's CVR number
     *
     * @param cvr CVR number
     * @return single company by CVR number
     * @throws exception.TheException
     */
    Company getCompany(String cvr) throws TheException;

    /**
     * Get all companies
     *
     * @return all companies
     * @throws exception.TheException
     */
    List<Company> getCompanies() throws TheException;

    /**
     * Get all companies with given zip code
     *
     * @param zipCode zip code
     * @return all companies located in given zip code
     * @throws exception.TheException
     */
    List<Company> getCompanies(String zipCode) throws TheException;

    /**
     * Get all companies with more employees than the number given
     *
     * @param employees employee count
     * @return all companies with more employees than given number
     * @throws exception.TheException
     */
    List<Company> getCompanies(int employees) throws TheException;

    /**
     * Add company to database and return it
     * @param company company
     * @return company added
     * @throws exception.TheException
     */
    Company addCompany(Company company) throws TheException;

    /**
     * Update a company in the database
     * @param company company
     * @return company updated
     * @throws exception.TheException
     */
    Company editCompany(Company company) throws TheException;

    /**
     * Delete company from database with given Id
     * @param id Identity Id
     * @return company deleted
     * @throws exception.TheException
     */
    Company deleteCompany(int id) throws TheException;

}
