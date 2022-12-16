package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.HousePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.*;
import com.neu.dimple.houserentalapplication.validator.HouseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    HouseValidator houseValidator;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @GetMapping("/user/residence/{residenceId}")
    @ResponseBody
    public List<House> getHouse(@PathVariable(value = "residenceId") UUID id){
        logger.info("Reached: GET /user/Residence/" + id);
        List<House> houseList;
        try{
            houseList = houseDAO.getHouseWithResidenceId(id);
        } catch (HouseException e) {
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
                    } catch (HouseException e) {
                        throw new RuntimeException(e);
                    }

                    for(House h: list){
                        List<HousePhoto> housePhotoList;
                        houseList.add(h);
                        try{
                            housePhotoList = housePhotoDAO.getAllHousePhotoWithHouseId(h.getId());
                        } catch (HouseException e) {
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

    @GetMapping("/user/addHouse.htm")
    public String handleGet(HttpSession session , ModelMap model, Residence residence, House house, HttpServletRequest request){
        logger.info("Reached GET /user/addHouse.htm: " + residence);

        model.addAttribute("residence", residence);
        model.addAttribute("house", house);
        User user = (User) session.getAttribute("username");

        String btnClicked = request.getParameter("btnClicked");

        if(btnClicked != null){
            if( btnClicked.equals("Add House") ){
                request.setAttribute("btnClicked", btnClicked);
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

    @PostMapping("/user/addHouse.htm")
    public String handleAddHousePost(HttpSession session , @ModelAttribute("residence") Residence residence, @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws ParseException {
        logger.info("Reached POST /user/addHouse.htm: " + residence);

        String addHouse = request.getParameter("addHouse");
        User user = (User) session.getAttribute("username");

        if(addHouse != null){
            houseValidator.validate(house, result);
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");

            if(result.hasErrors() || startdate == null || enddate == null || startdate.isEmpty() || enddate.isEmpty()){
                request.setAttribute("btnClicked", "Add House");
                List<Residence> residenceList;
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);
                if(startdate == null || startdate.isEmpty())
                    request.setAttribute("startdateerror", "Please enter start date");
                if(enddate == null || enddate.isEmpty())
                    request.setAttribute("enddateerror", "Please enter end date");
                return "addPost";
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            house.setStartdate(format.parse(startdate));
            house.setEnddate(format.parse(enddate));

            try{
                houseDAO.create(house);
            } catch (HouseException e) {
                System.out.println("Exception: " +e.getMessage());
            }

            status.setComplete();
            List<Residence> residenceList;
            try{
                residenceList = residenceDAO.getAllResidence(user.getId());
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("residenceList", residenceList);

            request.setAttribute("btnClicked", "Add House");
            request.setAttribute("houseAdded", "Successfully Added House: " + house.getHouseno());
            logger.info("Successfully Added House: " + house.getHouseno());
            return "addPost";
        }
        return "addPost";
    }

    @PostMapping("/user/deleteHouse.htm")
    public String handleDelete(HttpSession session , @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws HouseException {

        UUID houseDeleteId = UUID.fromString(request.getParameter("houseDeleteId"));
        logger.info("Reached Delete /user/deleteHouse.htm: " + houseDeleteId);
        User user = (User) session.getAttribute("username");
        try{
            houseDAO.deleteHouse(houseDeleteId);
        } catch (HouseException e) {
            System.out.println("Exception: " +e.getMessage());
        }

        List<HousePhoto> housephotolist;
        housephotolist = housePhotoDAO.getAllHousePhotoWithHouseId(houseDeleteId);

        for(HousePhoto housePhoto: housephotolist){
                housePhotoDAO.deleteHousePhoto(housePhoto.getId());
        }

        status.setComplete();
        request.setAttribute("btnClicked", "View House");

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
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }

            for(House h: list){
                List<HousePhoto> housePhotoList;
                houseList.add(h);
                try{
                    housePhotoList = housePhotoDAO.getAllHousePhotoWithHouseId(h.getId());
                } catch (HouseException e) {
                    throw new RuntimeException(e);
                }

                for(HousePhoto hp: housePhotoList){
                    housePhotos.add(hp);
                }

            }
        }
        request.setAttribute("housePhotos", housePhotos);
        request.setAttribute("houseList", houseList);

        request.setAttribute("houseDeleteSuccess", "Successfully Deleted House: " + houseDeleteId);
        logger.info("House Deleted Successfully: " + houseDeleteId);
        return "addPost";
    }

    @GetMapping("/user/updateHouse.htm")
    public String handleGetUpdate(HttpSession session, ModelMap model, House house, Residence residence, HttpServletRequest request) throws HouseException, ParseException {

        UUID houseeUpdateId = UUID.fromString(request.getParameter("houseeUpdateId"));
        logger.info("Reached GET /user/updateHouse.htm: " + houseeUpdateId);
        User user = (User) session.getAttribute("username");

        House updatedHouse = houseDAO.getHouse(houseeUpdateId);
        request.setAttribute("updatedHouse", updatedHouse);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(updatedHouse.getStartdate().toString());
        String startdate = format.format(date);
        request.setAttribute("startdate", startdate);

        date = format.parse(updatedHouse.getEnddate().toString());
        String enddate = format.format(date);
        request.setAttribute("enddate", enddate);

        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        model.addAttribute("house", house);
        model.addAttribute("residence", residence);

        String btnClicked = request.getParameter("updateHouse");
        request.setAttribute("btnClicked", btnClicked);
        return "addPost";
    }

    @PostMapping("/user/updateHouse.htm")
    public String handlePostUpdate(HttpSession session , @ModelAttribute("house") House house, BindingResult result, HttpServletRequest request, SessionStatus status) throws HouseException, ParseException {

        logger.info("Reached /user/updateHouse.htm: " + house.getHouseno());
        UUID houseUpdateId = UUID.fromString(request.getParameter("houseUpdateId"));
        house.setId(houseUpdateId);

        User user = (User) session.getAttribute("username");

        if(houseUpdateId != null){
            houseValidator.validate(house, result);

            if(result.hasErrors()){
                House updatedHouse = houseDAO.getHouse(houseUpdateId);
                request.setAttribute("updatedHouse", updatedHouse);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(updatedHouse.getStartdate().toString());
                String startdate = format.format(date);
                request.setAttribute("startdate", startdate);

                date = format.parse(updatedHouse.getEnddate().toString());
                String enddate = format.format(date);
                request.setAttribute("enddate", enddate);

                List<Residence> residenceList;
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);

                request.setAttribute("btnClicked", "Update House");
                return "addPost";
            }
            try{
                houseDAO.updateHouse(house);
            } catch (HouseException e) {
                System.out.println("Exception: " +e.getMessage());
            }

            request.setAttribute("btnClicked", "View House");

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
                } catch (HouseException e) {
                    throw new RuntimeException(e);
                }

                for(House h: list){
                    List<HousePhoto> housePhotoList;
                    houseList.add(h);
                    try{
                        housePhotoList = housePhotoDAO.getAllHousePhotoWithHouseId(h.getId());
                    } catch (HouseException e) {
                        throw new RuntimeException(e);
                    }

                    for(HousePhoto hp: housePhotoList){
                        housePhotos.add(hp);
                    }

                }
            }
            request.setAttribute("housePhotos", housePhotos);
            request.setAttribute("houseList", houseList);


            request.setAttribute("houseUpdateSuccess", "Successfully Updated House: " + house.getHouseno());
            logger.info("House Updated Successfully: " );

            return "addPost";
        }
        return "addPost";

    }

    }
