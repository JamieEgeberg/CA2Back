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

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public City getCity(int id) {
        return getCity(((long) id));
    }

    @Override
    public City getCity(long id) {
        return find(id);
    }

    @Override
    public City getCity(String zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE" +
                                                        " c.zipCode = :zipCode",
                                                City.class);
        query.setParameter("zipCode", zipCode);
        return query.getSingleResult();
    }

    @Override
    public List<City> getCities() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c",
                                                  City.class);
        return query.getResultList();
    }

    City find(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(City.class, id);
    }
}
