/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import data.CompanyFacade;
import data.ICompanyFacade;
import entity.Company;
import exception.NotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Jamie
 */
@Path("company")
public class CompanyResource {

    private ICompanyFacade facade = new CompanyFacade();
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
        facade.addEntityManagerFactory(
                Persistence.createEntityManagerFactory("PU"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanies() {
        return gson.toJson(facade.getCompanies());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyById(@PathParam("id") int id) {
        Company c =facade.getCompany(id);
        if(c==null){
                throw new NotFoundException("Company with id: "+id+" not found.");            
        }
        return gson.toJson(c);
    }

    @GET
    @Path("contactinfo/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompaniesContactInfo() {
        return gson.toJson(facade.getCompanies());
    }
    
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyContactInfoById(@PathParam("id") int id) {
        Company c =facade.getCompany(id);
        if(c==null){
                throw new NotFoundException("Company with id: "+id+" not found.");            
        }
        return gson.toJson(c);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCompany(String json) {
        Company company = gson.fromJson(json, Company.class);
        return gson.toJson(facade.addCompany(company));
    }

    /**
     * PUT method for updating or creating an instance of CompanyResource
     *
     * @param json
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editCompany(String json) {
        Company company = gson.fromJson(json, Company.class);
        return gson.toJson(facade.editCompany(company));
    }

    /**
     * DELETE method for removing an instance of CompanyResource
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCompany(@PathParam("id") int id) {
        facade.deleteCompany(id);
        return "{\"message\":\"Deleted person with id: \"" + id + "}";
    }
}
