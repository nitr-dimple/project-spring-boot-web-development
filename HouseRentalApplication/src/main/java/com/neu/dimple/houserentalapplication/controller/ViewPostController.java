package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class ViewPostController {

    @Autowired
    ResidenceDAO residenceDAO;

    @GetMapping("/user/viewPost.htm")
    public String handleGet(ModelMap model, Residence residence, House house){
        model.addAttribute("residence", residence);
        model.addAttribute("house", house);
        return "viewPost";
    }

//    @PostMapping("/user/viewPost.htm")
//    public String handlePost(HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {
//
//        String btnClicked = request.getParameter("btnClicked");
//        User user = (User) session.getAttribute("username");
//
//        if(btnClicked != null){
//            request.setAttribute("btnClicked", btnClicked);
//            if(btnClicked.equals("View Residence")){
//                List<Residence> residenceList;
//                try{
//                    residenceList = residenceDAO.getAllResidence(user.getId());
//                } catch (UserException e) {
//                    throw new RuntimeException(e);
//                }
//                request.setAttribute("residenceList", residenceList);
//            }
//            return "viewPost";
//        }
//        return "viewPost";
//    }

    }
