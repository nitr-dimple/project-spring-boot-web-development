package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class UserDAO extends DAO{
    public UserDAO() {
    }

    public User get(String email) throws UserException {
        try {

            begin();
            Query q = getSession().createQuery("from User where email= :email");
            q.setString("email", email);
            User user = (User) q.uniqueResult();

            commit();
            return user;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not create user " + email, e);
        }

    }
    public User create(User user) throws UserException {
        try {
            //save user object in the database
            begin();
            getSession().save(user);
            commit();
            close();

            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new UserException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user) throws UserException {
        try {
            //delete user object in the database
            begin();
            getSession().delete(user);
            commit();

        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new UserException("Exception while deleting user: " + e.getMessage());
        }
    }

}
