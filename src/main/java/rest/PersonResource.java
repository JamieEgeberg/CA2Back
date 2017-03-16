/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import data.IPersonFacade;
import data.PersonFacade;
import entity.Person;
import exception.TheException;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Jamie
 */
@Path("person")
public class PersonResource {

    private IPersonFacade facade = new PersonFacade();
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        facade.addEntityManagerFactory(
                Persistence.createEntityManagerFactory("PU"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() throws TheException {
        return gson.toJson(facade.getPersons());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) throws TheException {
        return gson.toJson(facade.getPerson(id));
    }

    /**
     * TODO: make it not return hobbies
     *
     * @return all persons
     * @throws exception.TheException
     */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactInfo() throws TheException {
        return gson.toJson(facade.getPersons());
    }

    /**
     * TODO: make it not return hobbies
     *
     * @param id Identity Id
     * @return single person
     * @throws exception.TheException
     */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfoById(@PathParam("id") int id) throws TheException {
        return gson.toJson(facade.getPerson(id));
    }

    @GET
    @Path("zip/{zipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByZip(@PathParam("zipCode") String zipCode) throws TheException {
        return gson.toJson(facade.getPersons(zipCode));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String json) throws TheException {
        Person person = gson.fromJson(json, Person.class);
        facade.addPerson(person);
        return gson.toJson(person);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(String json) throws TheException {
        Person person = gson.fromJson(json, Person.class);
        facade.editPerson(person);
        return gson.toJson(person);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) throws TheException {
        facade.deletePerson(id);
        return "{\"message\":\"Deleted person with id: \"" + id + "}";
    }

    @GET
    @Path("/test")
    public String testException() throws TheException {
        throw new TheException("Testing 1, 2, 3... 1, 2, 3...");
    }

}
