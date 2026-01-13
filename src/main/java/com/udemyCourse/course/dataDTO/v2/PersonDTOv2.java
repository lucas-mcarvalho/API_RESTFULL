package com.udemyCourse.course.dataDTO.v2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.udemyCourse.course.serializer.GenderSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonDTOv2 implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;


    private String firstname;
    private String lastname;
    private Date birthDayDate;
    private String addres;
    private String gender;

    public PersonDTOv2() {

    }

    public PersonDTOv2(Long id, String firstname, String lastname, String addres, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addres = addres;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDayDate() {
        return birthDayDate;
    }

    public void setBirthDayDate(Date birthDayDate) {
        this.birthDayDate = birthDayDate;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTOv2 that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(birthDayDate, that.birthDayDate) && Objects.equals(addres, that.addres) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, birthDayDate, addres, gender);
    }
}
