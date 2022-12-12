package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.HousePhoto;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class UserExploreController {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    HouseDAO houseDAO;

    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HousePhotoDAO housePhotoDAO;

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    @GetMapping("/user/explore.htm")
    public String getExplore(HttpSession session , HttpServletRequest request){
        logger.info("Reached: GET /user/explore.htm");

        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.get();
        } catch (ResidenceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList;
        try{
            houseList = houseDAO.get();
        } catch (HouseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("houseList", houseList);

        List<HousePhoto> housePhotos;
        try{
            housePhotos = housePhotoDAO.get();
        } catch (HouseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("housePhotos", housePhotos);

        List<ResidencePhoto> residencePhotoList;
        try{
            residencePhotoList = residencePhotoDAO.getAllResidencePhoto();
        } catch (ResidencePhotoException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residencePhotoList", residencePhotoList);

        return "explore";
    }
}
