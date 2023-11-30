package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configure the mail sender properties (host, port, username, password, etc.)
        // ...
        mailSender.setHost("smtp.gmail.com");

        // Set the mail server port (typically 587 for SMTP or 465 for SMTPS)
        mailSender.setPort(587);

        // Set the username and password for authentication
        mailSender.setUsername("codingsendemail200@gmail.com");
        mailSender.setPassword("hmei wxfy jhuj whbc");

        // Configure additional properties
        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return mailSender;
    }
}
