package com.udemyCourse.course.services;

import com.udemyCourse.course.config.EmailConfig;
import com.udemyCourse.course.dataDTO.request.EmailRequestDTO;
import com.udemyCourse.course.mail.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private EmailConfig emailConfig;

    public void sendSimpleEmail(EmailRequestDTO emailrequest) {
        try {
            emailSender.to(emailrequest.getTo()).withSubject(emailrequest.getSubject()).withMessage(emailrequest.getSubject()).send(emailConfig);

        }catch (MessagingException e){
            e.getMessage();
        }
    }
}

