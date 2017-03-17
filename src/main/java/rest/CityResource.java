/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import data.CityFacade;
import data.ICityFacade;
import exception.TheException;

import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Jamie
 */
@Path("city")
public class CityResource {

    private static Gson gson = new Gson();
    private ICityFacade facade = new CityFacade();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CityResource
     */
    public CityResource() {
        facade.addEntityManagerFactory(
                Persistence.createEntityManagerFactory("PU"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCities() throws TheException {
        return gson.toJson(facade.getCities());
    }

    @GET
    @Path("{zipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("zipCode") String zipCode)
            throws TheException {
        return gson.toJson(facade.getCity(zipCode));
    }

}
