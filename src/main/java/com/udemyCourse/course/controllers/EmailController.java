package com.udemyCourse.course.controllers;


import com.udemyCourse.course.controllers.docs.EmailControllerDocs;
import com.udemyCourse.course.dataDTO.request.EmailRequestDTO;
import com.udemyCourse.course.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailController implements EmailControllerDocs {

    @Autowired
    private EmailService service;



    @PostMapping
    @Override
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        service.sendSimpleEmail(emailRequest);
        return new ResponseEntity<>("E-mail sent With succes!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> sendEmailWithAttachment(String emailRequestJson, MultipartFile multipartFile) {
        return null;
    }
}
