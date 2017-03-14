/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import data.IPersonFacade;
import data.PersonFacade;

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

}
