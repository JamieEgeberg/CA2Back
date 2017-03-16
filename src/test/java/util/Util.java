/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.CityFacade;
import data.CompanyFacade;
import data.PersonFacade;
import entity.Address;
import entity.Company;
import entity.Hobby;
import entity.Person;
import exception.TheException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamie
 */
public class Util {

    public static void databaseInit(EntityManagerFactory emf) throws TheException {
        EntityManager em = emf.createEntityManager();

        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        CityFacade cf = new CityFacade();
        cf.addEntityManagerFactory(emf);
        CompanyFacade comf = new CompanyFacade();
        comf.addEntityManagerFactory(emf);

        List<Person> persons = new ArrayList<>();
        Address a1 = new Address("Avej 1", "1. th");
        Person p1 = new Person("Arne", "Arnested", "AA@mail.dk");
        a1.setCity(cf.getCity("1000"));
        p1.setAddress(a1);
        persons.add(p1);
        Address a2 = new Address("Bvej 2", "2. th");
        Person p2 = new Person("Berit", "Brandt", "BB@mail.dk");
        a2.setCity(cf.getCity("2000"));
        p2.setAddress(a2);
        persons.add(p2);
        Address a3 = new Address("Cvej 3", "3. th");
        Person p3 = new Person("Carl", "Conrad", "CC@mail.dk");
        a3.setCity(cf.getCity("3000"));
        p3.setAddress(a3);
        persons.add(p3);
        Address a4 = new Address("Dvej 4", "4. th");
        Person p4 = new Person("Doris", "Dram", "DD@mail.dk");
        a4.setCity(cf.getCity("4000"));
        p4.setAddress(a4);
        persons.add(p4);
        Address a5 = new Address("Evej 5", "5. th");
        Person p5 = new Person("Einar", "Eriksen", "EE@mail.dk");
        a5.setCity(cf.getCity("5000"));
        p5.setAddress(a5);
        persons.add(p5);

        em.getTransaction().begin();
        persons.forEach((p) -> em.persist(p));
        em.getTransaction().commit();

        List<Company> companies = new ArrayList<>();
        a1 = new Address("Hansensvej 42", "st th");
        Company c1 = new Company("Hansen A/S", "Lejligheder ud over det hele", "36363636", 42, 42000000, "info@hansen.dk");
        a1.setCity(cf.getCity("4200"));
        c1.setAddress(a1);
        companies.add(c1);

        a2 = new Address("Blomstervænget 22", "");
        Company c2 = new Company("Stuen Klokkeblomst", "Børnestue for blomsterbørn", "25252525", 22, 2200000, "stuen@klokkeblomst.dk");
        a2.setCity(cf.getCity("2200"));
        c2.setAddress(a2);
        companies.add(c2);

        a3 = new Address("Hovedvejen 22", "");
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
