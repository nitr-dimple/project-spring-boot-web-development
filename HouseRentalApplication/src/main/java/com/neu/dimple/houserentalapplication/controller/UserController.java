package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.handler.EmailHandler;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserDAO userDao;

    Logger logger = LoggerFactory.getLogger(HouseController.class);


    @GetMapping("/user/add.htm")
    public String handleGet(ModelMap model, User user){
        logger.info("Reached GET /user/add.htm");
        model.addAttribute("user", user);
        return "addUserFormView";
    }

    @PostMapping("/user/add.htm")
    public String addUserPOST( @ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        logger.info("Reached POST /user/add.htm");
        userValidator.validate(user, result);

        if (result.hasErrors())
            return "addUserFormView";

        User userExist = null;
        try {
            userExist = userDao.get(user.getEmail());
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        if(userExist != null){
            request.setAttribute("account-create-error", "Account with this email id already exists");
            return "addUserFormView";
        }

        //instantiate Hibernate objects, and save user

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        EmailHandler emailHandler = new EmailHandler();

        try {
            userDao.create(user);
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        logger.info("Calling sendEmail function for user: " + user.getEmail());
        emailHandler.sendEmail(user);


        //mark it complete
        status.setComplete();

        return "addUserSuccessView";
    }


    @GetMapping("/user/viewUser.htm")
    public String handleviewGet(HttpSession session, HttpServletRequest request){
        logger.info("Reached GET /user/viewUser.htm");

        return "viewUser";
    }

    @GetMapping("/user/updateUser.htm")
    public String handleupdateGet(HttpSession session, HttpServletRequest request, ModelMap model, User user){
        logger.info("Reached GET /user/updateUser.htm");

        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/user/updateUser.htm")
    public String addUserUpdatePOST(HttpSession session , @ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("Reached POST /user/updateUser.htm");
        User user1 = (User) session.getAttribute("username");
        user.setId(user1.getId());
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        user.setUsertype(user1.getUsertype());

        userValidator.validate(user, result);
        if (result.hasErrors())
            return "updateUser";

        logger.info("Updating user data");

        try {
            userDao.update(user);
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }
        request.setAttribute("userUpdateSucess", "Successfully Updated User " + user.getFullname());
        logger.info("Successfully Updated User " + user.getFullname());
        session.setAttribute("username", user);

        return "viewUser";
    }

    @PostMapping("/user/deleteUser.htm")
    public String handleDelete(HttpSession session , HttpServletRequest request, SessionStatus status) throws ParseException {

        logger.info("Reached DELETE /user/deleteUser.htm: ");

        User user = (User) session.getAttribute("username");
        try{
            userDao.delete(user);
        } catch (UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }

        logger.info("User Deleted Successfully: "  );
        return "deleteUserSuccessView";
    }

    @GetMapping("/user/visitHouse.htm")
    public String handleVisitHouse(HttpSession session , HttpServletRequest request, SessionStatus status) {

        logger.info("Reached GET /user/visitHouse.htm: ");
        return "visitUser";
    }

}
