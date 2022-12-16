package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.UserException;
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
public class UserDAO extends DAO {


    public UserDAO() {
    }

    public User getUser(UUID id) throws UserException {
        try {

            begin();
            User user = getSession().get(User.class, id);

            commit();
            close();
            return user;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not find user " + id, e);
        }
    }

    public List<User> getUserList() throws UserException {
        try{
            begin();
            Query q = getSession().createQuery("from User");
            List<User> userList = q.list();
            commit();
            close();
            return userList;
        }catch (HibernateException e){
            rollback();
            throw new UserException("Can not list all the users " + e.getMessage());
        }
    }

    public User get(String email) throws UserException {
        try {

            begin();
            Query q = getSession().createQuery("from User where email= :email");
            q.setString("email", email);
            User user = (User) q.uniqueResult();

            commit();
            close();
            return user;

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not get user " + email, e);
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
            throw new UserException("Exception while creating user: " + e.getMessage());
        }
    }

    public void update(User user) throws UserException {

        try {
            begin();
            getSession().update(user);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update user", e);
        }
    }

    public void delete(User user) throws UserException {
        try {
            //delete user object in the database
            begin();
            getSession().delete(user);
            commit();
            close();

        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while deleting user: " + e.getMessage());
        }
    }

}
