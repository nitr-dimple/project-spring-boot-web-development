package com.neu.dimple.houserentalapplication.pojo;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID residenceId;

    private int houseno;

    private String totalarea;

    private int bedrooms;

    private int bathrooms;

    private int balcony;

    private int livinrooms;

    private double advancepayment;

    private double monthrent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    private String description;

    private boolean waterbill;

    private boolean gasbill;

    private String parking;

    private boolean maintenancecost;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(UUID residenceId) {
        this.residenceId = residenceId;
    }

    public int getHouseno() {
        return houseno;
    }

    public void setHouseno(int houseno) {
        this.houseno = houseno;
    }

    public String getTotalarea() {
        return totalarea;
    }

    public void setTotalarea(String totalarea) {
        this.totalarea = totalarea;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public int getLivinrooms() {
        return livinrooms;
    }

    public void setLivinrooms(int livinrooms) {
        this.livinrooms = livinrooms;
    }

    public double getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(double advancepayment) {
        this.advancepayment = advancepayment;
    }

    public double getMonthrent() {
        return monthrent;
    }

    public void setMonthrent(double monthrent) {
        this.monthrent = monthrent;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isWaterbill() {
        return waterbill;
    }

    public void setWaterbill(boolean waterbill) {
        this.waterbill = waterbill;
    }

    public boolean isGasbill() {
        return gasbill;
    }

    public void setGasbill(boolean gasbill) {
        this.gasbill = gasbill;
    }

    public boolean isMaintenancecost() {
        return maintenancecost;
    }

    public void setMaintenancecost(boolean maintenancecost) {
        this.maintenancecost = maintenancecost;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }




}
