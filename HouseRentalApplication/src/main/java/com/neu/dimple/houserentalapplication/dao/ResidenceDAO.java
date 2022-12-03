package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class ResidenceDAO extends DAO{

    public ResidenceDAO() {
    }

    public Residence create(Residence residence) throws ResidenceException {
        try {
            //save user object in the database
            begin();
            getSession().save(residence);
            commit();
            close();

            return residence;
        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Exception while creating residence: " + e.getMessage());
        }
    }

    public Residence get(UUID id) throws UserException {
        try {

            begin();
            Query q = getSession().createQuery("from Residence where id= :id");
            q.setParameter("id", id);
            Residence residence = (Residence) q.uniqueResult();

            commit();
            return residence;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Cound not find residence with id " + id, e);
        }

    }

}
