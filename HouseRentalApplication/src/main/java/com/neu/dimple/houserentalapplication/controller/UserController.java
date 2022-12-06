package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserController {

    @Autowired
    UserValidator userValidator;

    @GetMapping("/user/add.htm")
    public String handleGet(ModelMap model, User user){
        model.addAttribute("user", user);
        return "addUserFormView";
    }

    @PostMapping("/user/add.htm")
    public String addUserPOST(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        userValidator.validate(user, result);

        if (result.hasErrors())
            return "addUserFormView";

        //instantiate Hibernate objects, and save user
        try {
            UserDAO userDao = new UserDAO();
            userDao.create(user);
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        //mark it complete
        status.setComplete();

        return "addUserSuccessView";
    }


}
