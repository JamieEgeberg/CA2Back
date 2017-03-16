/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import exception.TheExceptionMapper;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 * @author Jamie
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(exception.TheExceptionMapper.class);
        resources.add(rest.CityResource.class);
        resources.add(rest.CompanyResource.class);
        resources.add(rest.CorsRequestFilter.class);
        resources.add(rest.CorsResponseFilter.class);
        resources.add(rest.PersonResource.class);
    }
    
}
