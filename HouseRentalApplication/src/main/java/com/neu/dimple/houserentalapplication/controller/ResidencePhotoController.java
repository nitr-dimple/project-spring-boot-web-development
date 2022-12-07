package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class ResidencePhotoController {

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @GetMapping("/user/residencePhoto/{residenceId}")
    public List<ResidencePhoto> getResidencePhoto(@PathVariable(value = "residenceId") UUID id){
        logger.info("Reached: GET /user/residencePhoto/" + id);
        List<ResidencePhoto> residencePhotos;
        try{
            residencePhotos = residencePhotoDAO.getAllResidencePhotoWithResidenceId(id);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        logger.info("Successully fetched house data");
        return residencePhotos;
    }
}
