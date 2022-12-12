package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserLoginController  {

    @Autowired
    LoginValidator loginValidator;

    @GetMapping("/user/login.htm")
    public String handleGet(ModelMap model, User user){
        model.addAttribute("user", user);
        return "loginview";
    }
}
