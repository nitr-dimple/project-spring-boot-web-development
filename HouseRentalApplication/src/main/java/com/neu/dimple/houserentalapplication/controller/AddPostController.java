package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class AddPostController {
    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HouseDAO houseDAO;

    @Autowired
    HousePhotoDAO housePhotoDAO;

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);


    @GetMapping("/user/addPost.htm")
    public String handleGet(ModelMap model, Residence residence, House house){
        model.addAttribute("residence", residence);
        model.addAttribute("house", house);
        return "addPost";
    }

    @PostMapping("/user/addPost.htm")
    public String handlePost( HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {

        logger.info("Reached: POST /user/addPost.htm");

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

    @GetMapping("/user/viewPost.htm")
    public String handleViewPost(HttpSession session , HttpServletRequest request, SessionStatus status){

        logger.info("Reached: POST /user/viewPost.htm");

        String btnClicked = request.getParameter("btnClicked");
        User user = (User) session.getAttribute("username");
        request.setAttribute("btnClicked", btnClicked);


        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList = new ArrayList<>();
        List<ResidencePhoto> residencePhotoList = new ArrayList<>();
        List<HousePhoto> housePhotoList = new ArrayList<>();

        for(Residence residence: residenceList){
            List<House> houses;
            List<ResidencePhoto> residencePhotos;

            try{
                houses = houseDAO.getHouseWithResidenceId(residence.getId());
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }

            try{
                residencePhotos = residencePhotoDAO.getAllResidencePhotoWithResidenceId(residence.getId());
            } catch (UserException e) {
                throw new RuntimeException(e);
            }

            for(ResidencePhoto residencePhoto: residencePhotos)
                residencePhotoList.add(residencePhoto);

            for(House house: houses){
                houseList.add(house);

                List<HousePhoto> housePhotos;
                try{
                    housePhotos = housePhotoDAO.getAllHousePhotoWithHouseId(house.getId());
                } catch (HouseException e) {
                    throw new RuntimeException(e);
                }

                for(HousePhoto housePhoto: housePhotos)
                    housePhotoList.add(housePhoto);
            }
        }

        request.setAttribute("houseList", houseList);
        request.setAttribute("residencePhotoList", residencePhotoList);
        request.setAttribute("housePhotoList", housePhotoList);

        return "addPost";
    }

}
