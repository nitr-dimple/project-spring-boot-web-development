package com.neu.dimple.houserentalapplication.controller;

import com.neu.dimple.houserentalapplication.dao.BookHouseDAO;
import com.neu.dimple.houserentalapplication.dao.HouseDAO;
import com.neu.dimple.houserentalapplication.dao.ResidenceDAO;
import com.neu.dimple.houserentalapplication.dao.UserDAO;
import com.neu.dimple.houserentalapplication.exceptions.BookHouseException;
import com.neu.dimple.houserentalapplication.exceptions.HouseException;
import com.neu.dimple.houserentalapplication.exceptions.ResidenceException;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.BookHouse;
import com.neu.dimple.houserentalapplication.pojo.House;
import com.neu.dimple.houserentalapplication.pojo.Residence;
import com.neu.dimple.houserentalapplication.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Controller
public class BookHouseController {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    BookHouseDAO bookHouseDAO;

    @Autowired
    ResidenceDAO residenceDAO;

    @Autowired
    HouseDAO houseDAO;

    @Autowired
    UserDAO userDAO;


    @GetMapping("/user/bookHouse.htm")
    public String handleGetBookHouse(HttpServletRequest request){

        logger.info("Reached: GET /user/bookHouse.htm");
        String visitHouseId = request.getParameter("visitHouseId");
        request.setAttribute("visitHouseId", visitHouseId);

        return "confirmHouseBooking";
    }

    @PostMapping("/user/bookHouse.htm")
    public String handlePostBookHouse(HttpSession session, HttpServletRequest request){

        logger.info("Reached: POST /user/bookHouse.htm");
        User user = (User) session.getAttribute("username");

        String visitHouseId = request.getParameter("visitHouseId");

        BookHouse bookHouse = new BookHouse();
        bookHouse.setUserId(user.getId());
        bookHouse.setHouseId(UUID.fromString(visitHouseId));
        bookHouse.setBookingConfirmedfromUser(true);

        try{
           bookHouse = bookHouseDAO.create(bookHouse);
        } catch (BookHouseException e) {
            throw new RuntimeException(e);
        }

        logger.info("Successfully send request for House booking confirmation");

        request.setAttribute("sent-booking-request-success", "Booking Request is successfully sent to Owner of the House");

        return "confirmHouseBooking";
    }

    @GetMapping("/user/viewYourBooking.htm")
    public String handleGetViewYourBooking(HttpSession session, HttpServletRequest request){
        logger.info("Reached: GET /user/viewYourBooking.htm ");
        User user = (User) session.getAttribute("username");

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


        List<BookHouse> bookHouseList;
        try{
            bookHouseList = bookHouseDAO.getBookHouseWithUserId(user.getId());
        } catch (BookHouseException e) {
            throw new RuntimeException(e);
        }
        logger.info("size of bookHouseList " + bookHouseList.size() );
        request.setAttribute("bookHouseList", bookHouseList);
        return "viewUserBooking";

    }

    @GetMapping("/user/viewBooking.htm")
    public String handlerViewBooking(HttpServletRequest request, HttpSession session){

        logger.info("Reached GET /user/viewBooking.htm: ");

        String btnClicked = request.getParameter("btnClicked");
        request.setAttribute("btnClicked", btnClicked);
        User user = (User) session.getAttribute("username");


        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList = new ArrayList<>();
        List<BookHouse> bookHouseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        for(Residence residence: residenceList) {
            List<House> houses;
            try {
                houses = houseDAO.getHouseWithResidenceId(residence.getId());
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }
            for (House house : houses) {
                houseList.add(house);
                List<BookHouse> bookHouses;

                try{
                    bookHouses = bookHouseDAO.getBookHouseWithHouseId(house.getId());
                } catch (BookHouseException e) {
                    throw new RuntimeException(e);
                }

                for(BookHouse bh: bookHouses){
                    bookHouseList.add(bh);
                }
            }
        }

        try{
            userList = userDAO.getUserList();
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("houseList", houseList);
        request.setAttribute("bookHouseList", bookHouseList);
        request.setAttribute("userList", userList);

        return "viewVisitBooking";
    }

    @PostMapping("/user/updateBookingStatus.htm")
    public String handleUpdateBookingStatys(HttpServletRequest request, HttpSession session){
        logger.info("Reached: POST /user/updateBookingStatus.htm");

        String bookHouseId = request.getParameter("bookHouseId");
        String bookingStatus = request.getParameter("bookingStatus");

        BookHouse bookHouse;
        try{
            bookHouse = bookHouseDAO.get(UUID.fromString(bookHouseId));
        } catch (BookHouseException e) {
            throw new RuntimeException(e);
        }
        if(bookingStatus.equals("Accept Booking"))
            bookHouse.setBookingApprovedbyOwner("Accepted");
        else
            bookHouse.setBookingApprovedbyOwner("Denied");

        try{
            bookHouseDAO.update(bookHouse);
        } catch (BookHouseException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("bookhouse-status-update-success", "Successfully updated status");
        logger.info("Successfully updated bookhouse status");

        request.setAttribute("btnClicked", "View Booking");
        User user = (User) session.getAttribute("username");


        List<Residence> residenceList;
        try{
            residenceList = residenceDAO.getAllResidence(user.getId());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("residenceList", residenceList);

        List<House> houseList = new ArrayList<>();
        List<BookHouse> bookHouseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        for(Residence residence: residenceList) {
            List<House> houses;
            try {
                houses = houseDAO.getHouseWithResidenceId(residence.getId());
            } catch (HouseException e) {
                throw new RuntimeException(e);
            }
            for (House house : houses) {
                houseList.add(house);
                List<BookHouse> bookHouses;

                try{
                    bookHouses = bookHouseDAO.getBookHouseWithHouseId(house.getId());
                } catch (BookHouseException e) {
                    throw new RuntimeException(e);
                }

                for(BookHouse bh: bookHouses){
                    bookHouseList.add(bh);
                }
            }
        }

        try{
            userList = userDAO.getUserList();
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("houseList", houseList);
        request.setAttribute("bookHouseList", bookHouseList);
        request.setAttribute("userList", userList);

        return "viewVisitBooking";


    }


}
