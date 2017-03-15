/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Address;
import entity.Company;
import entity.Hobby;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamie
 */
public class MakeTable {

    public static void main(String[] args) {
        Persistence.generateSchema("PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        CityFacade cf = new CityFacade();
        cf.addEntityManagerFactory(emf);
        
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Arne", "Arnested", "AA@mail.dk"));
        persons.add(new Person("Berit", "Brandt", "BB@mail.dk"));
        persons.add(new Person("Carl", "Conrad", "CC@mail.dk"));
        persons.add(new Person("Doris", "Dram", "DD@mail.dk"));
        persons.add(new Person("Einar", "Eriksen", "EE@mail.dk"));

        em.getTransaction().begin();
        persons.forEach((p) -> em.persist(p));
        em.getTransaction().commit();

        List<Company> companies = new ArrayList<>();
        Address a1 = new Address("Hansensvej 42", "st th");
        Company c1 = new Company("Hansen A/S", "Lejligheder ud over det hele", "36363636", 42, 42000000, "info@hansen.dk");
        a1.setCity(cf.getCity("4200"));
        c1.setAddress(a1);
        companies.add(c1);

        Address a2 = new Address("Blomstervænget 22", "");
        Company c2 = new Company("Stuen Klokkeblomst", "Børnestue for blomsterbørn", "25252525", 22, 2200000, "stuen@klokkeblomst.dk");
        a2.setCity(cf.getCity("2200"));
        c2.setAddress(a2);
        companies.add(c2);

        Address a3 = new Address("Hovedvejen 22", "");
        Company c3 = new Company("Einar's Auto", "Biler repareres her!", "14141414", 36, 3600000, "einar@auto.dk");
        a3.setCity(cf.getCity("3600"));
        c3.setAddress(a3);
        companies.add(c3);

        em.getTransaction().begin();
        companies.forEach((c) -> em.persist(c));
        em.getTransaction().commit();

        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(new Hobby("Biking", "To ride a bike"));
        hobbies.add(new Hobby("Fitness", "To lift things made of metal"));
        hobbies.add(new Hobby("Gaming", "To play computergames"));
        hobbies.add(new Hobby("Knitting", "To make garments of wool using needles"));
        hobbies.add(new Hobby("Time Travel", "To travel through time"));
        
        em.getTransaction().begin();
        hobbies.forEach((h) -> em.persist(h));
        em.getTransaction().commit();
        em.close();
        

    }
}
