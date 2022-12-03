package com.neu.dimple.houserentalapplication.pojo;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
@Entity
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID userId;

    private String state;

    private String city;

    private String street;

    private String residencename;

    private long zipcode;

    public Residence() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getResidencename() {
        return residencename;
    }

    public void setResidencename(String residencename) {
        this.residencename = residencename;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return  residencename +", " + street + ", " + city + ", " + state + ", " + zipcode;
    }
}
