package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.*;
import org.hibernate.hql.internal.HolderInstantiator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class HouseController {

    @Autowired
    HouseDAO houseDAO;

    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HousePhotoDAO housePhotoDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @GetMapping("/user/residence/{residenceId}")
    @ResponseBody
    public List<House> getHouse(@PathVariable(value = "residenceId") UUID id){
        logger.info("Reached: GET /user/Residence/" + id);
        List<House> houseList;
        try{
            houseList = houseDAO.getHouseWithResidenceId(id);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        logger.info("Successully fetched house data");
        return houseList;
    }

    @PostMapping("/user/viewHouse.htm")
    public String handleViewPost(HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {
        logger.info("Reached: Post /user/viewHouse.htm");

        String btnClicked = request.getParameter("btnClicked");
        User user = (User) session.getAttribute("username");

        if(btnClicked != null){
            request.setAttribute("btnClicked", btnClicked);
            if(btnClicked.equals("View House")){
                List<Residence> residenceList;
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);

                List<House> houseList = new ArrayList<>();
                List<HousePhoto> housePhotos = new ArrayList<>();
                for(Residence r: residenceList){
                    List<House> list;
                    try{
                        list = houseDAO.getHouseWithResidenceId(r.getId());
                    } catch (UserException e) {
                        throw new RuntimeException(e);
                    }

                    for(House h: list){
                        List<HousePhoto> housePhotoList;
                        houseList.add(h);
                        try{
                            housePhotoList = housePhotoDAO.getAllHousePhotoWithHouseId(h.getId());
                        } catch (UserException e) {
                            throw new RuntimeException(e);
                        }

                        for(HousePhoto hp: housePhotoList){
                            housePhotos.add(hp);
                        }

                    }
                }

                request.setAttribute("housePhotos", housePhotos);
                request.setAttribute("houseList", houseList);

            }

            return "addPost";
        }
        return "addPost";
    }
}
