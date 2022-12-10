package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
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
public class ResidencePhotoDAO extends DAO{

    public ResidencePhotoDAO() {
    }

    public ResidencePhoto create(ResidencePhoto residencePhoto) throws ResidencePhotoException {
        try {
            //save user object in the database
            begin();
            getSession().save(residencePhoto);
            commit();
            close();

            return residencePhoto;
        } catch (HibernateException e) {
            rollback();
            throw new ResidencePhotoException("Exception while creating residence: " + e.getMessage());
        }
    }

    public List<ResidencePhoto> getAllResidencePhotoWithResidenceId(UUID id) throws UserException {
        try {
            begin();
            Query q = getSession().createQuery("from ResidencePhoto where residenceId= :id");
            q.setParameter("id", id);
            List<ResidencePhoto> residencePhotos = q.list();

            commit();
            close();
            return residencePhotos;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not find residence photo with residence id " + id, e);
        }
    }

    public List<ResidencePhoto> getAllResidencePhoto() throws ResidencePhotoException {
        try {
            begin();
            List<ResidencePhoto> residencePhotos = getSession().createCriteria(ResidencePhoto.class).list();

            commit();
            close();
            return residencePhotos;

        } catch (HibernateException e) {
            rollback();
            throw new ResidencePhotoException("Could not find residence photo with residence id " + e);
        }
    }

    public void deleteResidencePhoto(UUID id) throws ResidenceException {
        try {
            begin();
            Query q = getSession().createQuery("delete ResidencePhoto where id= :id");
            q.setParameter("id", id);
            q.executeUpdate();
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Could not delete residence photo", e);
        }
    }


}
