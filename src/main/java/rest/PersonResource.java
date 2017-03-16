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
    public String getPersons() {
        return gson.toJson(facade.getPersons());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {
        return gson.toJson(facade.getPerson(id));
    }

    /**
     * TODO: make it not return hobbies
     *
     * @return all persons
     */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactInfo() {
        return gson.toJson(facade.getPersons());
    }

    /**
     * TODO: make it not return hobbies
     *
     * @param id Identity Id
     * @return single person
     */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfoById(@PathParam("id") int id) {
        return gson.toJson(facade.getPerson(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String json) {
        Person person = gson.fromJson(json, Person.class);
        facade.addPerson(person);
        return gson.toJson(person);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(String json) {
        Person person = gson.fromJson(json, Person.class);
        facade.editPerson(person);
        return gson.toJson(person);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) {
        facade.deletePerson(id);
        return "{\"message\":\"Deleted person with id: \"" + id + "}";
    }

    @GET
    @Path("/test")
    public String testException() throws TheException {
        throw new TheException("Testing 1, 2, 3... 1, 2, 3...");
    }

}
