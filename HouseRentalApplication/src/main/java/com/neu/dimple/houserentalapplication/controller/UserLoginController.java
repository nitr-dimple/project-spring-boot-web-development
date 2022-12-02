package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.LoginValidator;
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
import javax.servlet.http.HttpSession;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserLoginController {

    @Autowired
    LoginValidator loginValidator;

    @GetMapping("/user/login.htm")
    public String handleGet(ModelMap model, User user){
        model.addAttribute("user", user);
        return "loginview";
    }

    @PostMapping("/user/login.htm")
    public String addUserPOST(HttpSession session ,@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception
    {

        loginValidator.validate(user, result);

        if (result.hasErrors())
            return "loginview";

        User user1 = null;

        try {
            UserDAO userDao = new UserDAO();
            user1 = userDao.get(user.getEmail());
        }
        catch(UserException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        //mark it complete
        status.setComplete();

        if(user1 == null ){
            request.setAttribute("error", "User with entered email id does not exists");
            return "loginview";
        }
        else if(!user1.getPassword().equals(user.getPassword())){
            request.setAttribute("error", "Please enter valid password");
            return "loginview";
        }
        else if(!user1.getUsertype().equals(user.getUsertype())){
            request.setAttribute("error", "You do not have account of type " + user.getUsertype());
            return "loginview";
        }

        session.setAttribute("username", user1.getFullname());

        return "addUserSuccessView";
    }
}
