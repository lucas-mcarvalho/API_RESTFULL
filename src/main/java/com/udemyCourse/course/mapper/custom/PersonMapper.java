package com.udemyCourse.course.mapper.custom;

import com.udemyCourse.course.dataDTO.v2.PersonDTOv2;
import com.udemyCourse.course.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOv2 convertEntityToDTO(Person person){
        PersonDTOv2 dto = new PersonDTOv2();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setBirthDayDate(new Date());
        dto.setLastname(person.getLastname());
        dto.setAddres(person.getAddres());
        dto.setGender(person.getGender());
        return dto;
    }


    public Person DTOtoEntity(Person person){
        Person dto = new Person();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());
        dto.setAddres(person.getAddres());
        dto.setGender(person.getGender());
        return dto;
    }

}
