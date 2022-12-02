package com.neu.dimple.houserentalapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserLogoutController {

    @GetMapping("/user/logout.htm")
    public String addUserPOST(HttpSession session) throws Exception{
        session.removeAttribute("username");
        return "welcome";
    }

}
