package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.ResidenceValidator;
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
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class ResidenceController {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    ResidenceValidator residenceValidator;
    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    @GetMapping("/user/addResidence.htm")
    public String handleGet(ModelMap model, Residence residence, HttpServletRequest request){
        model.addAttribute("residence", residence);
        String btnClicked = request.getParameter("btnClicked");
        request.setAttribute("btnClicked", btnClicked);
        return "addPost";
    }

    @PostMapping("/user/addResidence.htm")
    public String handlePost(HttpSession session , @ModelAttribute("residence") Residence residence, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {

        logger.info("Reached /user/addResidence.htm: " + residence);

        String addResidence = request.getParameter("addResidence");
        User user = (User) session.getAttribute("username");

        if(addResidence != null){
            logger.info("Button Clicked " + addResidence);
            logger.info("Residence details Passed: " + residence);

            residenceValidator.validate(residence, result);
            if(result.hasErrors()){
                request.setAttribute("btnClicked", "Add Residence");
                return "addPost";
            }
            Residence residenceCreated = null;
            try{
                residence.setUserId(user.getId());
                residenceCreated = residenceDAO.create(residence);
            } catch (ResidenceException e) {
                System.out.println("Exception: " +e.getMessage());
            }
            status.setComplete();
            request.setAttribute("btnClicked", "Add Residence");
            request.setAttribute("residenceAdded", "Successfully Added residence: " + residence.getResidencename());
            logger.info("Residence Added Successfully: " + residenceCreated);

            return "addPost";
        }
        return "addPost";
    }

    @PostMapping("/user/viewResidence.htm")
    public String handleViewPost(HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {

        String btnClicked = request.getParameter("btnClicked");
        User user = (User) session.getAttribute("username");

        if(btnClicked != null){
            request.setAttribute("btnClicked", btnClicked);
            if(btnClicked.equals("View Residence")){
                List<Residence> residenceList;
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);

                List<ResidencePhoto> residencePhotos;
                try{
                    residencePhotos = residencePhotoDAO.getAllResidencePhoto();
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residencePhotos", residencePhotos);
            }
            return "addPost";
        }
        return "addPost";
    }

    @PostMapping("/user/deleteResidence.htm")
    public String handleDelete(HttpSession session , @ModelAttribute("residence") Residence residence, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {

        UUID residenceDeleteId = UUID.fromString(request.getParameter("residenceDeleteId"));
        logger.info("Reached /user/deleteResidence.htm: " + residenceDeleteId);
        User user = (User) session.getAttribute("username");
        try{
            residenceDAO.deleteResidence(residenceDeleteId);
        } catch (ResidenceException e) {
            System.out.println("Exception: " +e.getMessage());
        }
        status.setComplete();
        request.setAttribute("btnClicked", "View Residence");

        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<ResidencePhoto> residencePhotos;
        try{
            residencePhotos = residencePhotoDAO.getAllResidencePhoto();
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residencePhotos", residencePhotos);

        request.setAttribute("residenceDeleteSuccess", "Successfully Deleted Residence: " + residenceDeleteId);
        logger.info("Residence Deleted Successfully: " + residenceDeleteId);
        return "addPost";
    }

    @GetMapping("/user/updateResidence.htm")
    public String handleGetUpdate(ModelMap model, Residence residence, HttpServletRequest request) throws UserException {

        UUID residenceUpdateId = UUID.fromString(request.getParameter("residenceUpdateId"));
        logger.info("Reached /user/updateResidence.htm: " + residenceUpdateId);

        Residence updatedResidence = residenceDAO.getResidence(residenceUpdateId);
        request.setAttribute("updatedResidence", updatedResidence);

        model.addAttribute("residence", residence);
        String btnClicked = request.getParameter("updateResidence");
        request.setAttribute("btnClicked", btnClicked);
        return "addPost";
    }
    @PostMapping("/user/updateResidence.htm")
    public String handleUpdate(HttpSession session , @ModelAttribute("residence") Residence residence, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException, UserException {

        logger.info("Reached /user/updateResidence.htm: " + residence);
        UUID residenceUpdateId = UUID.fromString(request.getParameter("residenceUpdateId"));
        residence.setId(residenceUpdateId);

        User user = (User) session.getAttribute("username");

        if(residenceUpdateId != null){
            residenceValidator.validate(residence, result);

            if(result.hasErrors()){
                Residence updatedResidence = residenceDAO.getResidence(residenceUpdateId);
                request.setAttribute("updatedResidence", updatedResidence);
                request.setAttribute("btnClicked", "Update Residence");
                return "addPost";
            }
            try{
                residence.setUserId(user.getId());
                residenceDAO.updateResidence(residence);
            } catch (ResidenceException e) {
                System.out.println("Exception: " +e.getMessage());
            }
            List<Residence> residenceList;
            try{
                residenceList = residenceDAO.getAllResidence(user.getId());
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
            status.setComplete();
            request.setAttribute("residenceList", residenceList);

            List<ResidencePhoto> residencePhotos;
            try{
                residencePhotos = residencePhotoDAO.getAllResidencePhoto();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("residencePhotos", residencePhotos);

            request.setAttribute("btnClicked", "View Residence");
            request.setAttribute("residenceUpdateSuccess", "Successfully Updated residence: " + residence.getResidencename());
            logger.info("Residence Updated Successfully: " );

            return "addPost";
        }
        return "addPost";
    }


}
