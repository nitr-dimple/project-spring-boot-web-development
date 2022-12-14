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
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID userId;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID houseId;

    private String email;

    private long phonenumber;

    private String description;

    public Visit() {
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

    public UUID getHouseId() {
        return houseId;
    }

    public void setHouseId(UUID houseId) {
        this.houseId = houseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
