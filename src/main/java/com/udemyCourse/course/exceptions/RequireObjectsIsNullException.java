package com.udemyCourse.course.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireObjectsIsNullException extends RuntimeException {


    public RequireObjectsIsNullException() {

        super("It is not allowed to persist a null objetct");
    }

    public RequireObjectsIsNullException(String message) {

        super(message);
    }

}
