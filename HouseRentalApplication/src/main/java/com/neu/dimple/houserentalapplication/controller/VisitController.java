package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.VisitDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.exceptions.VisitException;
import com.neu.dimple.houserentalapplication.pojo.*;
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
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HouseDAO houseDAO;


    @GetMapping("/user/visitHouse.htm")
    public String handleVisitHouse(Visit visit, ModelMap modelMap,  HttpServletRequest request) {

        logger.info("Reached GET /user/visitHouse.htm: ");
        String visitHouseId = request.getParameter("visitHouseId");
        request.setAttribute("visitHouseId", visitHouseId);

        modelMap.addAttribute("visit", visit);
        return "visitUser";
    }

    @PostMapping("/user/visitHouse.htm")
    public String handVisitHousePost(HttpSession session , @ModelAttribute("visit") Visit visit, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response){

        logger.info("Reached POST /user/visitHouse.htm");
        String visitHouseId = request.getParameter("visitHouseId");
        visit.setHouseId(UUID.fromString(visitHouseId));
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

    @GetMapping("/user/viewVisitBooking.htm")
    public String handleVisitBooking(HttpSession session , HttpServletRequest request, SessionStatus status) {

        logger.info("Reached GET /user/viewVisitBooking.htm: ");
        return "viewVisitBooking";
    }

    @GetMapping("user/viewVisits.htm")
    public String handleViewVisit(HttpSession session , HttpServletRequest request, SessionStatus status) {

        logger.info("Reached GET /user/viewVisits.htm: ");
        String btnClicked = request.getParameter("btnClicked");
        request.setAttribute("btnClicked", btnClicked);

        User user = (User) session.getAttribute("username");

        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList = new ArrayList<>();
        List<Visit> visitList = new ArrayList<>();

        for(Residence residence: residenceList){
            List<House> houses;
            try{
                houses = houseDAO.getHouseWithResidenceId(residence.getId());
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }

            for(House house: houses){
                houseList.add(house);
                List<Visit> visits;
                try{
                    visits = visitDAO.getVisitByHouseId(house.getId());
                } catch (VisitException e) {
                    throw new RuntimeException(e);
                }

                for(Visit visit: visits)
                    visitList.add(visit);
            }
        }

        request.setAttribute("houseList", houseList);
        request.setAttribute("visitList", visitList);

        return "viewVisitBooking";
    }

    @PostMapping("/user/markVisitComplete.htm")
    public String handlemarkVisitComplete(HttpSession session , HttpServletRequest request, SessionStatus status) {

        logger.info("Reached POST /user/markVisitComplete.htm: ");
        request.setAttribute("btnClicked", "View Visits");
        String visitId = request.getParameter("visitId");
        String value = request.getParameter("markVisitComplete");
        boolean visitStatus;
        if(value.equals("Mark Visit Complete"))
            visitStatus = true;
        else
            visitStatus = false;

        logger.info("Updating visiting status for visit: " + visitId);

        try{
            visitDAO.updateVisitStatus(UUID.fromString(visitId), visitStatus);
        } catch (VisitException e) {
            throw new RuntimeException(e);
        }
        logger.info("Successfully Updated visiting status");

        User user = (User) session.getAttribute("username");

        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList = new ArrayList<>();
        List<Visit> visitList = new ArrayList<>();

        for(Residence residence: residenceList){
            List<House> houses;
            try{
                houses = houseDAO.getHouseWithResidenceId(residence.getId());
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }

            for(House house: houses){
                houseList.add(house);
                List<Visit> visits;
                try{
                    visits = visitDAO.getVisitByHouseId(house.getId());
                } catch (VisitException e) {
                    throw new RuntimeException(e);
                }

                for(Visit visit: visits)
                    visitList.add(visit);
            }
        }

        request.setAttribute("houseList", houseList);
        request.setAttribute("visitList", visitList);
        if(visitStatus)
            request.setAttribute("visit-update-success", "Successfully Mark visit as completed");
        else
            request.setAttribute("visit-update-success", "Successfully Mark visit as not completed");

        return "viewVisitBooking";
    }


}