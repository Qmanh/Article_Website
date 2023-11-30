package com.laptrinhjavaweb.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller(value="mailControllerOfWeb")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/trang-chu/send-mail",method = RequestMethod.POST)
    public String doSendEmail(HttpServletRequest request) {

        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("quanmanh567@gmail.com");
        email.setSubject("Test send email");
        email.setText("Hello I'm a root");

        // sends the e-mail
        mailSender.send(email);

        // forwards to the view named "Result"
        return "web/home";
    }

}
