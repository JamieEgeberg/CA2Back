/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jamie
 */
public class MakeTable {

    public static void main(String[] args) {
        Persistence.generateSchema("PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

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
        companies.add(new Company("Hansen A/S", "Lejligheder ud over det hele", "36363636", 42, 42000000, "info@hansen.dk"));
        companies.add(new Company("Stuen Klokkeblomst", "Børnestue for blomsterbørn", "25252525", 22, 2200000, "stuen@klokkeblomst.dk"));
        companies.add(new Company("Einar's Auto", "Biler repareres her!", "14141414", 36, 3600000, "info@hansen.dk"));

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
        
        

    }
}
