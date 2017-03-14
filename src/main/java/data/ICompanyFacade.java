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

    void addEntityManagerFactory(EntityManagerFactory emf);

    Company getCompany(int id);

    Company getCompany(long id);

    Company getCompany(String cvr);

    List<Company> getCompanies();

    List<Company> getCompanies(String zipCode);

    List<Company> getCompanies(int employees);

    Company addCompany(Company company);
    Company editCompany(Company company);
    Company deleteCompany(int id);

}
