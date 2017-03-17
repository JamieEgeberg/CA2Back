/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author Jamie
 */
@Entity
public class Company extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String cvr;
    private int noOfEmployees;
    private int marketValue;

    public Company() {
    }

    public Company(String name, String description, String cvr, int noOfEmployees, int marketValue, String email) {
        super(email);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.noOfEmployees = noOfEmployees;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

}
