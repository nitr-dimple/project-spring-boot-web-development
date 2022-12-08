package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.*;
import com.neu.dimple.houserentalapplication.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class HousePhotoController {

    @Autowired
    HousePhotoDAO housePhotoDAO;

    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HouseDAO houseDAO;

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @PostMapping("/user/addHousePhoto.htm")
    public String handleHousePhotoPost(HttpSession session , @RequestParam("houseImage") CommonsMultipartFile imagefile, HttpServletRequest request){

        String uploadHousePhoto = request.getParameter("uploadHousePhoto");
        User user = (User) session.getAttribute("username");

        if(uploadHousePhoto != null) {
            HousePhoto housePhoto = new HousePhoto();
            UUID residenceId = UUID.fromString(request.getParameter("residenceId"));
            housePhoto.setResidenceId(residenceId);
            String fileName = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
            byte[] data = imagefile.getBytes();
            String path = session.getServletContext().getRealPath("/")+ "images/"+fileName;
            housePhoto.setImagename(fileName);
            housePhoto.setHouseId(UUID.fromString(request.getParameter("houseId")));
            try{
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(data);
                fos.close();
                System.out.println("File Uploaded successfully");
            }catch (IOException e) {
                e.printStackTrace();
                System.out.println("File Upload error");
            }
            try{
                housePhotoDAO.create(housePhoto);
            }catch (HousePhotoException e){
                System.out.println("Exception: " +e.getMessage());
            }

        }
        List<Residence> residenceList = new ArrayList<>();
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);
        request.setAttribute("btnClicked", "Add House Photo");
        request.setAttribute("uploadSuccess", "Image Uploaded Successfully");
        return "addPost";
    }

    @PostMapping("/user/deleteHousePhoto.htm")
    public String handleDelete(HttpSession session , HttpServletRequest request, SessionStatus status) throws ParseException {

        UUID housePhotoDeleteId = UUID.fromString(request.getParameter("housePhotoDeleteId"));
        logger.info("Reached Delete /user/deleteHousePhoto.htm: " + housePhotoDeleteId);
        User user = (User) session.getAttribute("username");
        try{
            housePhotoDAO.deleteHousePhoto(housePhotoDeleteId);
        } catch (HouseException e) {
            System.out.println("Exception: " +e.getMessage());
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

        request.setAttribute("housePhotoDeleteSuccess", "Successfully Deleted House Photo: " + housePhotoDeleteId);
        logger.info("House Deleted Successfully: " + housePhotoDeleteId);
        return "addPost";
    }
}
