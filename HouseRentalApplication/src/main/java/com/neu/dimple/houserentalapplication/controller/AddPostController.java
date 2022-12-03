package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.ResidenceValidator;
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
public class AddPostController {

    @Autowired
    ResidenceValidator residenceValidator;

    @GetMapping("/user/addPost.htm")
    public String handleGet(ModelMap model, Residence residence){
        model.addAttribute("residence", residence);
        return "addPost";
    }

    @PostMapping("/user/addPost.htm")
    public String handlePost(HttpSession session , @ModelAttribute("residence") Residence residence, BindingResult result, HttpServletRequest request, SessionStatus status){

        String btnClicked = request.getParameter("btnClicked");
        if(btnClicked != null){
            request.setAttribute("btnClicked", btnClicked);
            return "addPost";
        }

        String addResidence = request.getParameter("addResidence");
        if(addResidence != null){
            residenceValidator.validate(residence, result);

            if(result.hasErrors()){
                request.setAttribute("btnClicked", "Add Residence");
                return "addPost";
            }

            try{
                ResidenceDAO residenceDAO = new ResidenceDAO();
                User user = (User) session.getAttribute("username");
                residence.setUserId(user.getId());
                residenceDAO.create(residence);
            } catch (ResidenceException e) {
                System.out.println("Exception: " +e.getMessage());
            }

            status.setComplete();

            request.setAttribute("btnClicked", "Add Residence");
            request.setAttribute("residenceAdded", "Successfully Added residence: " + residence.getResidencename());

            return "addPost";


        }

        return "addPost";
    }
}
