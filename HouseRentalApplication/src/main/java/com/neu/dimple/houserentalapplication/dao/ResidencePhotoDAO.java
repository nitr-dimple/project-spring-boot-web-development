package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

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
}
