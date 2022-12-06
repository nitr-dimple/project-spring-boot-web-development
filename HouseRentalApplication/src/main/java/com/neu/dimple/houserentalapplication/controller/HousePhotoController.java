package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.HousePhotoDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.exceptions.HousePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.HousePhoto;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
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

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @PostMapping("/user/addHousePhoto.htm")
    public String handleResidnecePhotoPost(HttpSession session , @RequestParam("houseImage") CommonsMultipartFile imagefile, HttpServletRequest request){

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
}
