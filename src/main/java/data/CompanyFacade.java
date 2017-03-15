/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import util.Utility;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Jamie
 */
public class CompanyFacade implements ICompanyFacade {

    EntityManagerFactory emf = null;

    /**
     * Add an Entity Manager Factory which will be used for when generating
     * Entity Managers for the other methods
     *
     * @param emf
     */
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     */
    @Override
    public Company getCompany(int id) {
        return getCompany((long) id);
    }

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     */
    @Override
    public Company getCompany(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Company.class, id);
    }

    /**
     * Get a single company by it's CVR number
     *
     * @param cvr CVR number
     * @return single company by CVR number
     */
    @Override
    public Company getCompany(String cvr) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        query.setParameter("cvr", cvr);
        return query.getResultList().get(0);
    }

    /**
     * Get all companies
     *
     * @return all companies
     */
    @Override
    public List<Company> getCompanies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c", Company.class);
        return query.getResultList();
    }

    /**
     * Get all companies with given zip code
     *
     * @param zipCode zip code
     * @return all companies located in given zip code
     */
    @Override
    public List<Company> getCompanies(String zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.address.city.zipCode = :zipCode", Company.class);
        query.setParameter("zipCode", zipCode);
        return query.getResultList();
    }

    /**
     * Get all companies with more employees than the number given
     *
     * @param employees employee count
     * @return all companies with more employees than given number
     */
    @Override
    public List<Company> getCompanies(int employees) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.noOfEmployees > :employees", Company.class);
        query.setParameter("employees", employees);
        return query.getResultList();
    }

    @Override
    public Company addCompany(Company c) {
        Utility.persist(emf, c);
        return c;
    }

    @Override
    public Company editCompany(Company c) {
        Utility.merge(emf, c);
        return c;
    }

    @Override
    public Company deleteCompany(int id) {
       return deleteCompany((long) id);
    }
    
    public Company deleteCompany(long id) {
        EntityManager em = emf.createEntityManager();
        Company toBeRemoved = null;
        try {
            em.getTransaction().begin();
            toBeRemoved = em.merge(em.find(Company.class, id));
            em.remove(toBeRemoved);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return toBeRemoved;
    }

}
