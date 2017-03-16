/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import exception.TheException;
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
    public Company getCompany(int id) throws TheException {
        return getCompany((long) id);
    }

    /**
     * Get a single company by it's Identity id
     *
     * @param id Identity id
     * @return single company by id
     */
    @Override
    public Company getCompany(long id) throws TheException {
        EntityManager em = emf.createEntityManager();
        Company c = em.find(Company.class, id);
        if (c == null) {
            throw new TheException("Company with id: " + id + " not found.", 404, false);
        }
        return c;
    }

    /**
     * Get a single company by it's CVR number
     *
     * @param cvr CVR number
     * @return single company by CVR number
     */
    @Override
    public Company getCompany(String cvr) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        query.setParameter("cvr", cvr);
        Company c = query.getResultList().get(0);
        if (c == null) {
            throw new TheException("Company with cvr: " + cvr + " not found.", 404, false);
        }
        return c;
    }

    /**
     * Get all companies
     *
     * @return all companies
     */
    @Override
    public List<Company> getCompanies() throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c", Company.class);
        List<Company> c = query.getResultList();
        if (c.size() <= 0) {
            throw new TheException("No companies found.", 404, false);
        }
        return c;
    }

    /**
     * Get all companies with given zip code
     *
     * @param zipCode zip code
     * @return all companies located in given zip code
     */
    @Override
    public List<Company> getCompanies(String zipCode) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.address.city.zipCode = :zipCode", Company.class);
        query.setParameter("zipCode", zipCode);
        List<Company> c = query.getResultList();
        if (c.size() <= 0) {
            throw new TheException("No companies found with zip: " + zipCode + ".", 404, false);
        }
        return c;
    }

    /**
     * Get all companies with more employees than the number given
     *
     * @param employees employee count
     * @return all companies with more employees than given number
     */
    @Override
    public List<Company> getCompanies(int employees) throws TheException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Company> query = em.createQuery(
                "SELECT c FROM Company c WHERE c.noOfEmployees > :employees", Company.class);
        query.setParameter("employees", employees);
        List<Company> c = query.getResultList();
        if (c.size() <= 0) {
            throw new TheException("No companies found with more than: " + employees + " employees.", 404, false);
        }
        return c;
    }

    @Override
    public Company addCompany(Company c) throws TheException {
        Utility.persist(emf, c);
        return c;
    }

    @Override
    public Company editCompany(Company c) throws TheException {
        Utility.merge(emf, c);
        return c;
    }

    @Override
    public Company deleteCompany(int id) throws TheException {
        return deleteCompany((long) id);
    }

    public Company deleteCompany(long id) throws TheException {
        EntityManager em = emf.createEntityManager();
        Company toBeRemoved = null;
        try {
            em.getTransaction().begin();
            toBeRemoved = em.merge(em.find(Company.class, id));
            em.remove(toBeRemoved);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new TheException("Delete failed. Company with id: " + id + " not found.", 404, false);
        } finally {
            em.close();
        }
        return toBeRemoved;
    }

}
