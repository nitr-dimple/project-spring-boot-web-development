package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
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

    public List<Residence> getAllResidence(UUID id) throws UserException {
        try {
            begin();
            Query q = getSession().createQuery("from Residence where userId= :id");
            q.setParameter("id", id);
            List<Residence> residences = q.list();

            commit();
            return residences;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Cound not find residence with id " + id, e);
        }
    }

    public void deleteResidence(UUID id) throws ResidenceException {
        try {
            begin();
            Query q = getSession().createQuery("delete Residence where id= :id");
            q.setParameter("id", id);
            q.executeUpdate();
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Could not delete residence", e);
        }
    }

}
