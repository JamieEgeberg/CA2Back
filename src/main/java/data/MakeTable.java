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
import entity.Phone;
import exception.TheException;

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

    public static void main(String[] args) throws TheException {
        Persistence.generateSchema("PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        CityFacade cf = new CityFacade();
        cf.addEntityManagerFactory(emf);

        List<Hobby> h = new ArrayList<>();
        h.add(new Hobby("Biking", "To ride a bike"));
        h.add(new Hobby("Fitness", "To lift things made of metal"));
        h.add(new Hobby("Gaming", "To play computer games"));
        h.add(new Hobby("Knitting", "To make garments of wool using needles"));
        h.add(new Hobby("Time Travel", "To travel through time"));

        em.getTransaction().begin();
        h.forEach((hobby) -> em.persist(hobby));
        em.getTransaction().commit();

        List<Person> persons = new ArrayList<>();
        Address a1 = new Address("Avej 1", "1. th");
        Phone p1phone1 = new Phone("11111111", "Aphone1");
        Phone p1phone2 = new Phone("22222222", "Aphone2");
        Person p1 = new Person("Arne", "Arnested", "AA@mail.dk");
        a1.setCity(cf.getCity("1000"));
        p1.setAddress(a1);
        p1.phones.add(p1phone1);
        p1.phones.add(p1phone2);
        p1.hobbies.add(h.get(0));
        p1.hobbies.add(h.get(1));
        persons.add(p1);
        Address a2 = new Address("Bvej 2", "2. th");
        Phone p2phone1 = new Phone("33333333", "Bphone1");
        Phone p2phone2 = new Phone("44444444", "Bphone2");
        Person p2 = new Person("Berit", "Brandt", "BB@mail.dk");
        a2.setCity(cf.getCity("2000"));
        p2.setAddress(a2);
        p2.phones.add(p2phone1);
        p2.phones.add(p2phone2);
        p2.hobbies.add(h.get(3));
        p2.hobbies.add(h.get(4));
        persons.add(p2);
        Address a3 = new Address("Cvej 3", "3. th");
        Phone p3phone1 = new Phone("55555555", "Cphone1");
        Phone p3phone2 = new Phone("66666666", "Cphone2");
        Person p3 = new Person("Carl", "Conrad", "CC@mail.dk");
        a3.setCity(cf.getCity("3000"));
        p3.setAddress(a3);
        p3.phones.add(p3phone1);
        p3.phones.add(p3phone2);
        p3.hobbies.add(h.get(2));
        persons.add(p3);
        Address a4 = new Address("Dvej 4", "4. th");
        Phone p4phone1 = new Phone("7777777", "Dphone1");
        Person p4 = new Person("Doris", "Dram", "DD@mail.dk");
        a4.setCity(cf.getCity("4000"));
        p4.setAddress(a4);
        p4.phones.add(p4phone1);
        p4.hobbies.add(h.get(1));
        p4.hobbies.add(h.get(3));
        persons.add(p4);
        Address a5 = new Address("Evej 5", "5. th");
        Person p5 = new Person("Einar", "Eriksen", "EE@mail.dk");
        a5.setCity(cf.getCity("5000"));
        p5.setAddress(a5);
        p5.hobbies.add(h.get(0));
        persons.add(p5);

        em.getTransaction().begin();
        persons.forEach((p) -> em.persist(p));
        em.getTransaction().commit();

        List<Company> companies = new ArrayList<>();
        a1 = new Address("Hansensvej 42", "st th");
        Phone c1phone1 = new Phone("42424242", "Hansen A/S reception");
        Phone c1phone2 = new Phone("42424243", "Hansen A/S direktion");
        Company c1 = new Company("Hansen A/S", "Lejligheder ud over det hele", "36363636", 42, 42000000, "info@hansen.dk");
        a1.setCity(cf.getCity("4200"));
        c1.setAddress(a1);
        c1.phones.add(c1phone1);
        c1.phones.add(c1phone2);
        companies.add(c1);

        a2 = new Address("Blomstervænget 25", "");
        Phone c2phone1 = new Phone("25252525", "Klokkeblomst kontor");
        Company c2 = new Company("Stuen Klokkeblomst", "Børnestue for blomsterbørn", "25252525", 25, 2500000, "stuen@klokkeblomst.dk");
        a2.setCity(cf.getCity("2200"));
        c2.setAddress(a2);
        c2.phones.add(c2phone1);
        companies.add(c2);

        a3 = new Address("Hovedvejen 22", "");
        Company c3 = new Company("Einar's Auto", "Biler repareres her!", "14141414", 36, 3600000, "einar@auto.dk");
        a3.setCity(cf.getCity("3600"));
        c3.setAddress(a3);
        companies.add(c3);

        em.getTransaction().begin();
        companies.forEach((c) -> em.persist(c));
        em.getTransaction().commit();

        em.close();

    }
}
