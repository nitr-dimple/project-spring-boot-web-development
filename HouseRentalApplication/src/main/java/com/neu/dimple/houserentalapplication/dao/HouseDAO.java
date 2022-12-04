package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.pojo.House;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class HouseDAO extends DAO{

    public HouseDAO() {
    }

    public House create(House house) throws HouseException {
        try {
            //save user object in the database
            begin();
            getSession().save(house);
            commit();
            close();

            return house;
        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Exception while creating house: " + e.getMessage());
        }
    }
}
