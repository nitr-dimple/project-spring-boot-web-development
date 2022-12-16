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
public class BookHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID userId;

    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID houseId;

    private boolean bookingConfirmedfromUser;

    private String bookingApprovedbyOwner;

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

    public boolean isBookingConfirmedfromUser() {
        return bookingConfirmedfromUser;
    }

    public void setBookingConfirmedfromUser(boolean bookingConfirmedfromUser) {
        this.bookingConfirmedfromUser = bookingConfirmedfromUser;
    }

    public String getBookingApprovedbyOwner() {
        return bookingApprovedbyOwner;
    }

    public void setBookingApprovedbyOwner(String bookingApprovedbyOwner) {
        this.bookingApprovedbyOwner = bookingApprovedbyOwner;
    }
}
