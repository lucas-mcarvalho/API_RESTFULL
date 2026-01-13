package com.udemyCourse.course.controllers;


import com.udemyCourse.course.services.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {


    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping(value = "/test")
    public String testLog(){
        logger.debug("This is debug log");
        logger.info("This is info log");
        logger.warn("This is war log");
        logger.error("This is error log");

        return "Logs generated succesfully!";
    }
}
