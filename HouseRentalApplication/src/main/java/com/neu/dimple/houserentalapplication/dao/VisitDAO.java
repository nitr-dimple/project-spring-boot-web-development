package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.VisitException;
import com.neu.dimple.houserentalapplication.pojo.Visit;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public List<Visit> getVisitByHouseId(UUID id) throws VisitException {
        try {

            begin();
            Query q = getSession().createQuery("from Visit where houseId= :id");
            q.setParameter("id", id);
            List<Visit> visits = q.list();

            commit();
            close();
            return visits;

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
            throw new VisitException("Exception while creating visit: " + e.getMessage());
        }
    }

    public void updateVisitStatus(UUID id, boolean visitStatus) throws VisitException {
        try {
            //save user object in the database
            begin();
            getSession().createQuery("update Visit v set v.visitingStatus = :visitStatus where id = : id")
                    .setParameter("id", id)
                    .setParameter("visitStatus", visitStatus)
                    .executeUpdate();
            commit();
            close();

            return;
        } catch (HibernateException e) {
            rollback();
            throw new VisitException("Exception while updating visit status: " + e.getMessage());
        }
    }

    public void update(Visit visit) throws VisitException {
        try {
            //save user object in the database
            begin();
            getSession().update(visit);
            commit();
            close();

            return;
        } catch (HibernateException e) {
            rollback();
            throw new VisitException("Exception while updating visit: " + e.getMessage());
        }
    }

    public List<Visit> getVistByUser(UUID id) throws VisitException{
        try{
            begin();
            Query q = getSession().createQuery("from Visit where userId = : id");
            q.setParameter("id", id);
            List<Visit> visitList = q.list();
            commit();
            close();
            return visitList;
        }catch (HibernateException e){
            rollback();
            throw new VisitException("Exception while fetching visit with id: " + id);
        }
    }
}
