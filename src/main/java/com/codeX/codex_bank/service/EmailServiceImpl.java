    package com.codeX.codex_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.EmailDetails;

@Service
public class EmailServiceImpl  implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendAlert(EmailDetails emailDetails) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setTo(emailDetails.getReciptient());
            simpleMailMessage.setText(emailDetails.getMessageBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());    

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email sent successfully");


        }catch (Exception e){
            System.out.println("Error sending email: "+e.getMessage());

        }
    }
}
