package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.controller.HouseController;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.pojo.UserEmailToken;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class UserEmailDAO extends DAO {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    public UserEmailDAO() {
    }

    public UserEmailToken createToken(UserEmailToken userEmailToken) throws UserException {
        try {
            //save user object in the database
            logger.info("Saving object : " + userEmailToken);
            begin();
            getSession().save(userEmailToken);
            commit();
            close();

            return userEmailToken;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating user token: " + e.getMessage());
        }
    }

    public UserEmailToken getToken(UUID id) throws UserException {
        try {
            //save user object in the database
            begin();
            UserEmailToken userEmailToken =  getSession().get(UserEmailToken.class, id);
            commit();
            close();

            return userEmailToken;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while fetching user token: " + e.getMessage());
        }
    }
}
