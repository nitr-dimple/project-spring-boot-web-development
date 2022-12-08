package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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

    public List<House> getHouseWithResidenceId(UUID id) throws HouseException {
        try {
            begin();
            Query q = getSession().createQuery("from House where residenceId= :id");
            q.setParameter("id", id);
            List<House> houseList = q.list();

            commit();
            return houseList;

        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not get house info " + id, e);
        }
    }

    public void deleteHouse(UUID id) throws HouseException {
        try {
            begin();
            Query q = getSession().createQuery("delete House where id= :id");
            q.setParameter("id", id);
            q.executeUpdate();
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not delete house", e);
        }
    }

    public House getHouse(UUID id) throws HouseException {
        try {
            begin();
            House house = getSession().get(House.class, id);;
            commit();
            return house;

        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not find house with id " + id, e);
        }
    }

    public void updateHouse(House house) throws HouseException {
        try {
            begin();
            getSession().update(house);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new HouseException("Could not update house", e);
        }
    }
}
