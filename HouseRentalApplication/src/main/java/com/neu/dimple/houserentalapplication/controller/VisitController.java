package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.VisitDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.exceptions.VisitException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.pojo.Visit;
import com.neu.dimple.houserentalapplication.validator.VisitorValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class VisitController {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    VisitorValidator validator;

    @Autowired
    VisitDAO visitDAO;


    @GetMapping("/user/visitHouse.htm")
    public String handleVisitHouse(Visit visit, ModelMap modelMap,  HttpServletRequest request) {

        logger.info("Reached GET /user/visitHouse.htm: ");
        String visitHouseId = request.getParameter("visitHouseId");
        visit.setHouseId(UUID.fromString(visitHouseId));

        modelMap.addAttribute("visit", visit);
        return "visitUser";
    }

    @PostMapping("/user/visitHouse.htm")
    public String handVisitHousePost(HttpSession session , @ModelAttribute("visit") Visit visit, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response){

        logger.info("Reached POST /user/visitHouse.htm");
        validator.validate(visit, result);


        if(result.hasErrors())
            return "visitUser";

        User user = (User) session.getAttribute("username");


        visit.setUserId(user.getId());
        try {
            visitDAO.create(visit);
        }
        catch(VisitException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        logger.info("Successfully  Created visit: " + user.getEmail());
        request.setAttribute("visit-success", "Send your details Successfully");

        return "visitUser";
    }
}
