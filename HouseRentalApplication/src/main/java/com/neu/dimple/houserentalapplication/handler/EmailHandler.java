package com.neu.dimple.houserentalapplication.handler;

import com.neu.dimple.houserentalapplication.controller.HouseController;
import com.neu.dimple.houserentalapplication.dao.UserEmailDAO;
import com.neu.dimple.houserentalapplication.exceptions.UserException;
import com.neu.dimple.houserentalapplication.pojo.User;
import com.neu.dimple.houserentalapplication.pojo.UserEmailToken;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

/**
 * @author Dimpleben Kanjibhai Patel
 */

public class EmailHandler {


    Logger logger = LoggerFactory.getLogger(HouseController.class);

    public void sendEmail(User user){

        logger.info("Reached sendEmail");

        String firstName = user.getFullname();
        String sendgridKey = System.getenv("SENDGRID_KEY");;
        String domainName = "dimplepatel.me";
        String serverName = "localhost:8080";
        String fromId = "noreply@" + domainName;
        String emailId = user.getEmail();
        long now = Instant.now().getEpochSecond();
        long ttl = 60*5;
        UserEmailDAO userEmailDAO = new UserEmailDAO();


        UserEmailToken userEmailToken = new UserEmailToken();
        userEmailToken.setUserId(user.getId());
        userEmailToken.setExpirationTime(ttl + now);

        logger.info("Creating User Token to verify the account: " + userEmailToken.getUserId() + " " + userEmailToken.getExpirationTime());

        try{
            userEmailToken = userEmailDAO.createToken(userEmailToken);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }

        String token = userEmailToken.getTokenId().toString();
        String link = "http://" + serverName + "/HouseRental/user/verifyUserEmail?email=" + emailId + "&token=" + token;

        String message = "Hi " + firstName + ",  \n\n" +
                "Thank you for registration, \n" +
                "Your token will expire in 60 sec \n\n" +
                "Please click on the below link to verify your account: \n\n" +
                link + "\n\n Regards, \n" + domainName + " \n";
        String subject = "Verification Email";
        Email to = new Email(emailId);

        Email from = new Email(fromId);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendgridKey);
        logger.info("Sending an email to user : " + user.getId());

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info("Email Sent successfully");

        } catch (IOException ex) {
            try {
                throw ex;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
