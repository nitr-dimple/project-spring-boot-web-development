package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.HousePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.pojo.HousePhoto;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

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

}
