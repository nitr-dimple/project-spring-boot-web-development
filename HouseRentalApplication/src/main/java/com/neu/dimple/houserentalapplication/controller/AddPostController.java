package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
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
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class AddPostController {
    @Autowired
    ResidenceDAO residenceDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);


    @GetMapping("/user/addPost.htm")
    public String handleGet(ModelMap model, Residence residence, House house){
        model.addAttribute("residence", residence);
        model.addAttribute("house", house);
        return "addPost";
    }

    @PostMapping("/user/addPost.htm")
    public String handlePost( HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {

        logger.info("Reached: POSt /user/addPost.htm");

        String btnClicked = request.getParameter("btnClicked");
        User user = (User) session.getAttribute("username");

        if(btnClicked != null){
            request.setAttribute("btnClicked", btnClicked);
            if(btnClicked.equals("Add Residence Photo") || btnClicked.equals("Add House") || btnClicked.equals("Add House Photo")){
                List<Residence> residenceList;
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);
            }
            return "addPost";
        }
        return "addPost";
    }

}
