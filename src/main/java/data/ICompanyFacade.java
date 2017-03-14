package data;

import entity.Company;

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
     */
    Company getCompany(int id);

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     */
    Company getCompany(long id);

    /**
     * Get a single company by it's CVR number
     *
     * @param cvr CVR number
     * @return single company by CVR number
     */
    Company getCompany(String cvr);

    /**
     * Get all companies
     *
     * @return all companies
     */
    List<Company> getCompanies();

    /**
     * Get all companies with given zip code
     *
     * @param zipCode zip code
     * @return all companies located in given zip code
     */
    List<Company> getCompanies(String zipCode);

    /**
     * Get all companies with more employees than the number given
     *
     * @param employees employee count
     * @return all companies with more employees than given number
     */
    List<Company> getCompanies(int employees);

    /**
     * Add company to database and return it
     * @param company company
     * @return company added
     */
    Company addCompany(Company company);

    /**
     * Update a company in the database
     * @param company company
     * @return company updated
     */
    Company editCompany(Company company);

    /**
     * Delete company from database with given Id
     * @param id Identity Id
     * @return company deleted
     */
    Company deleteCompany(int id);

}
