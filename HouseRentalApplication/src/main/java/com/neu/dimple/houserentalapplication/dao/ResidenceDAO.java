package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.controller.HouseController;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class ResidenceDAO extends DAO{

    Logger logger = LoggerFactory.getLogger(HouseController.class);


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

    public List<Residence> get() throws ResidenceException {
        try {

            begin();
            Query q = getSession().createQuery("from Residence");
            List<Residence> residences = q.list();
            commit();
            close();
            return residences;

        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Could not list residence", e);
        }
    }

    public List<Residence> getAllResidence(UUID id) throws UserException {
        logger.info("Fetching residence list DAO: ");

        try {
            begin();
            Query q = getSession().createQuery("from Residence where userId= :id");
            q.setParameter("id", id);
            List<Residence> residences = q.list();
            for(Residence res: residences)
                logger.info("Fetched residence list DAO: " + residences);
            commit();
            close();
            return residences;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not find residence with id " + id, e);
        }
    }

    public Residence getResidence(UUID id) throws UserException {
        try {
            begin();
            Residence residence = getSession().get(Residence.class, id);;
            commit();
            close();
            return residence;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not find residence with id " + id, e);
        }
    }
    public void deleteResidence(UUID id) throws ResidenceException {
        try {
            begin();
            Query q = getSession().createQuery("delete Residence where id= :id");
            q.setParameter("id", id);
            q.executeUpdate();
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Could not delete residence", e);
        }
    }

    public void updateResidence(Residence residence) throws ResidenceException {
        logger.info("Updating  residence DAO: " + residence);

        try {
            begin();
            getSession().update(residence);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new ResidenceException("Could not update residence", e);
        }
    }

}
