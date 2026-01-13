package com.udemyCourse.course.controllers;

import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.dataDTO.v2.PersonDTOv2;
import com.udemyCourse.course.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
     private PersonServices service;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO findById(@PathVariable("id") Long id){
        var person = service.findById(id);
        person.setDate(new Date());
        //person.setPhoneNumber("62995008765");
        person.setPhoneNumber("");
        person.setLastname(null);
        person.setSensitiveData("Foo Bar");
        return person;
    }

    @RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
    public List<PersonDTO> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes =
                    {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO create(@RequestBody PersonDTO PersonDTO){
            return service.create(PersonDTO);
    }

    @RequestMapping(method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO update(@RequestBody PersonDTO PersonDTO){
        return service.update(PersonDTO);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }


    @RequestMapping(value ="/v2" , method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
    public PersonDTOv2 createV2(@RequestBody PersonDTOv2 PersonDTOv2){
        return service.createv2(PersonDTOv2);
    }

}
