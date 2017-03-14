/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jamie
 */
public class CompanyFacade implements ICompanyFacade {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("PU");

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Company getCompany(int id) {
        return getCompany((long) id);
    }

    @Override
    public Company getCompany(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Company.class, id);
    }

    @Override
    public Company getCompany(String cvr) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        query.setParameter("cvr", cvr);
        return query.getSingleResult();
    }

    @Override
    public List<Company> getCompanies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c", Company.class);
        return query.getResultList();
    }

    @Override
    public List<Company> getCompanies(String zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.address.city.zipCode = :zipCode", Company.class);
        query.setParameter("zipCode", zipCode);
        return query.getResultList();
    }

    @Override
    public List<Company> getCompanies(int employees) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.noOfEmployees > :employees", Company.class);
        query.setParameter("employees", employees);
        return query.getResultList();
    }

}
