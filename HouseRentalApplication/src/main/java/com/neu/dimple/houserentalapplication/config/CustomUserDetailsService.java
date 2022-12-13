package com.neu.dimple.houserentalapplication.config;

import com.neu.dimple.houserentalapplication.controller.HouseController;
import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private HttpServletRequest request;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Reached loadUserByUsername : " + username);
        HttpSession session = request.getSession();
        User user = null;
        try {
            user = userDao.get(username);
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }
        logger.info("Loaded User:" + user);


        if (null == user ) {
            throw new UsernameNotFoundException("Username not found");
        }

        if(!user.isVerified()){
            throw new UsernameNotFoundException("Please Verify your account");
        }

        if(!user.isAccountActiveStatus()){
            throw new UsernameNotFoundException("Your account is deactivated");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        logger.info("Role of the User:" + customUserDetails.getAuthorities());
        session.setAttribute("username", user);

        return customUserDetails;
    }

}
