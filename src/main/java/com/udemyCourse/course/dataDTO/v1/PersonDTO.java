package com.udemyCourse.course.dataDTO.v1;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.udemyCourse.course.serializer.GenderSerializer;
import org.hibernate.annotations.Filter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


//@JsonPropertyOrder({"id","firstname","lastname","gender","addres"})
@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
   private Date date;
   // @JsonProperty("fist_name")
    private String firstname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;
    private  String addres;

    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    private String sensitiveData;

    public PersonDTO(){

    }

    public PersonDTO(Long id, String firstname, String lastname, String addres, String gender) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(id, personDTO.id) && Objects.equals(date, personDTO.date) && Objects.equals(firstname, personDTO.firstname) && Objects.equals(lastname, personDTO.lastname) && Objects.equals(phoneNumber, personDTO.phoneNumber) && Objects.equals(addres, personDTO.addres) && Objects.equals(gender, personDTO.gender) && Objects.equals(sensitiveData, personDTO.sensitiveData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, firstname, lastname, phoneNumber, addres, gender, sensitiveData);
    }
}
