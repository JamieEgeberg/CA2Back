package data;

import entity.City;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Niki on 2017-03-14.
 *
 * @author Niki
 */
public interface ICityFacade {

    void addEntityManagerFactory(EntityManagerFactory emf);

    City getCity(int id);

    City getCity(long id);

    City getCity(String zipCode);

    List<City> getCities();

}
