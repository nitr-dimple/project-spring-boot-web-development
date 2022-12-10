package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.*;
import com.neu.dimple.houserentalapplication.pojo.HousePhoto;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class HousePhotoDAO extends DAO{
    public HousePhotoDAO() {
    }

    public HousePhoto create(HousePhoto housePhoto) throws HousePhotoException {
        try {
            //save user object in the database
            begin();
            getSession().save(housePhoto);
            commit();
            close();

            return housePhoto;
        } catch (HibernateException e) {
            rollback();
            throw new HousePhotoException("Exception while adding house photo: " + e.getMessage());
        }
    }

    public List<HousePhoto> get() throws HouseException {
        try {
            begin();
            Query q = getSession().createQuery("from HousePhoto");
            List<HousePhoto> housePhotos = q.list();

            commit();
            close();
            return housePhotos;

        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not list house photo", e);
        }
    }

    public List<HousePhoto> getAllHousePhotoWithHouseId(UUID id) throws HouseException {
        try {
            begin();
            Query q = getSession().createQuery("from HousePhoto where houseId= :id");
            q.setParameter("id", id);
            List<HousePhoto> housePhotos = q.list();

            commit();
            close();
            return housePhotos;

        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not find house photo with house id " + id, e);
        }
    }

    public void deleteHousePhoto(UUID id) throws HouseException {
        try {
            begin();
            Query q = getSession().createQuery("delete HousePhoto where id= :id");
            q.setParameter("id", id);
            q.executeUpdate();
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not delete house photo", e);
        }
    }

}
