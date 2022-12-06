package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@RestController
public class HouseController {

    @Autowired
    HouseDAO houseDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @GetMapping("/user/residence/{residenceId}")
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
}
