package com.udemyCourse.course.dataDTO.request;

import java.util.Objects;

public class EmailRequestDTO {

    private  String to;
    private  String body;
    private  String subject;


    public EmailRequestDTO() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EmailRequestDTO that)) return false;
        return Objects.equals(to, that.to) && Objects.equals(body, that.body) && Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, body, subject);
    }
}
