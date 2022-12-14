package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.exceptions.VisitException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.pojo.Visit;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class VisitDAO extends DAO{

    public VisitDAO() {
    }

    public Visit getVisit(UUID id) throws VisitException {
        try {

            begin();
            Visit visit = getSession().get(Visit.class, id);

            commit();
            close();
            return visit;

        } catch (HibernateException e) {
            rollback();
            throw new VisitException("Could not find visit " + id, e);
        }
    }

    public Visit getVisitByHouseId(UUID id) throws VisitException {
        try {

            begin();
            Query q = getSession().createQuery("from Visit where userId= :id");
            q.setParameter("id", id);
            Visit visit = (Visit) q.uniqueResult();

            commit();
            close();
            return visit;

        } catch (HibernateException e) {
            rollback();
            throw new VisitException("Could not get visit with houseId " + id, e);
        }
    }

    public Visit create(Visit visit) throws VisitException {
        try {
            //save user object in the database
            begin();
            getSession().save(visit);
            commit();
            close();

            return visit;
        } catch (HibernateException e) {
            rollback();
            throw new VisitException("Exception while creating user: " + e.getMessage());
        }
    }
}
