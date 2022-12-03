package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.ResidencePhotoDAO;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.ResidencePhotoException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.ResidencePhoto;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.validator.ResidenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
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
public class AddPostController {

    @Autowired
    ResidenceValidator residenceValidator;

    @Autowired
    ResidencePhotoDAO residencePhotoDAO;

    @GetMapping("/user/addPost.htm")
    public String handleGet(ModelMap model, Residence residence){
        model.addAttribute("residence", residence);
        return "addPost";
    }

    @PostMapping("/user/addPost.htm")
    public String handlePost( HttpSession session , @ModelAttribute("residence") Residence residence, BindingResult result, HttpServletRequest request, SessionStatus status){

        String btnClicked = request.getParameter("btnClicked");
        ResidenceDAO residenceDAO = new ResidenceDAO();
        User user = (User) session.getAttribute("username");

        if(btnClicked != null){
            request.setAttribute("btnClicked", btnClicked);
            if(btnClicked.equals("Add Residence Photo")){
                List<Residence> residenceList = new ArrayList<>();
                try{
                    residenceList = residenceDAO.getAllResidence(user.getId());
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("residenceList", residenceList);
            }
            return "addPost";
        }

        String addResidence = request.getParameter("addResidence");
        if(addResidence != null){
            residenceValidator.validate(residence, result);

            if(result.hasErrors()){
                request.setAttribute("btnClicked", "Add Residence");
                return "addPost";
            }

            try{
                residence.setUserId(user.getId());
                residenceDAO.create(residence);
            } catch (ResidenceException e) {
                System.out.println("Exception: " +e.getMessage());
            }

            status.setComplete();

            request.setAttribute("btnClicked", "Add Residence");
            request.setAttribute("residenceAdded", "Successfully Added residence: " + residence.getResidencename());

            return "addPost";

        }



        return "addPost";
    }

    @PostMapping("/user/addResidencePhoto.htm")
    public String handleResidnecePhotoPost(HttpSession session , @RequestParam("imagename") CommonsMultipartFile imagefile, HttpServletRequest request){

        String uploadResidencePhoto = request.getParameter("uploadResidencePhoto");

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
        request.setAttribute("btnClicked", "Add Residence Photo");
        request.setAttribute("uploadSuccess", "File Uploaded Successfully");
        return "addPost";
    }
}
