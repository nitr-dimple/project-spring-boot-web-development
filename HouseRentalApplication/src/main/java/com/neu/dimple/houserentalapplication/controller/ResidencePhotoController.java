package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class ResidencePhotoController {

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    @Autowired
    ResidenceDAO residenceDAO;

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
        logger.info("Successully fetched all residence photo");
        return residencePhotos;
    }

    @PostMapping("/user/addResidencePhoto.htm")
    public String handleResidnecePhotoPost(HttpSession session , @RequestParam("imagename") CommonsMultipartFile imagefile, HttpServletRequest request){

        String uploadResidencePhoto = request.getParameter("uploadResidencePhoto");
        User user = (User) session.getAttribute("username");

        if(uploadResidencePhoto != null) {
            ResidencePhoto residencePhoto = new ResidencePhoto();
            UUID residenceId = UUID.fromString(request.getParameter("residence"));
            residencePhoto.setResidenceId(residenceId);
            String fileName = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
            byte[] data = imagefile.getBytes();
            String path = session.getServletContext().getRealPath("/")+ "images/"+fileName;
            residencePhoto.setImagename(fileName);
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
                residencePhotoDAO.create(residencePhoto);
            }catch (ResidencePhotoException e){
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
        request.setAttribute("btnClicked", "Add Residence Photo");
        request.setAttribute("uploadSuccess", "File Uploaded Successfully");
        return "addPost";
    }


    @PostMapping("/user/deleteResidencePhoto.htm")
    public String handleDelete(HttpSession session , HttpServletRequest request, SessionStatus status) throws ParseException {

        UUID residencePhotoDeleteId = UUID.fromString(request.getParameter("residencePhotoDeleteId"));
        logger.info("Reached Delete /user/deleteResidencePhoto.htm: " + residencePhotoDeleteId);
        User user = (User) session.getAttribute("username");
        try{
            residencePhotoDAO.deleteResidencePhoto(residencePhotoDeleteId);
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
        } catch (ResidencePhotoException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residencePhotos", residencePhotos);

        request.setAttribute("residencePhotoDeleteSuccess", "Successfully Deleted ResidencePhoto: " + residencePhotoDeleteId);
        logger.info("Residence Deleted Successfully: " + residencePhotoDeleteId);
        return "addPost";
    }


}
