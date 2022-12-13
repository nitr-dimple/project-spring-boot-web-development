package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.dao.UserEmailDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.pojo.UserEmailToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class VerifyEmailController {

    @Autowired
    UserEmailDAO userEmailDAO;

    @Autowired
    UserDAO userDAO;

    Logger logger = LoggerFactory.getLogger(VerifyEmailController.class);

    @GetMapping("/user/verifyUserEmail")
    public String verifyEmail(@RequestParam(value = "email") String email, @RequestParam(value = "token") String token, HttpServletRequest request) throws UserException {
        logger.info("Reached get verify email call");
        UserEmailToken userEmailToken;

        try{
            userEmailToken = userEmailDAO.getToken(UUID.fromString(token));
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        User user;

        try{
            user = userDAO.getUser(userEmailToken.getUserId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }

        if(user == null){
            request.setAttribute( "authentication-error","Invalid User");
            return "loginview";
        }

        if(!user.getEmail().equals(email)){
            request.setAttribute( "authentication-error","Failed to verify with this email");
            return "loginview";
        }

        long now = Instant.now().getEpochSecond(); // unix time

        if(userEmailToken.getExpirationTime() < now) {
            request.setAttribute( "authentication-error","Token is expired");
            return "loginview";
        }

        logger.info("Verifying User ..." + user.getId());
        user.setVerified(true);
        user.setAccountActiveStatus(true);
        userDAO.update(user);
        request.setAttribute( "authentication-success","Successfully Verified you account. You can Login now");
        return "loginview";

    }
}
