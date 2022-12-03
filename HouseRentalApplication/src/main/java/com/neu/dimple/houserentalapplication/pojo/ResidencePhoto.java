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
public class ResidencePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID residenceId;

    private String imagename;

    public ResidencePhoto() {
    }

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

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
