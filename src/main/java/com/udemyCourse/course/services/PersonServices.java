package com.udemyCourse.course.services;

import com.udemyCourse.course.controllers.PersonController;
import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.dataDTO.v2.PersonDTOv2;
import com.udemyCourse.course.exceptions.RequireObjectsIsNullException;
import com.udemyCourse.course.exceptions.ResourceNotFoundException;
import static com.udemyCourse.course.mapper.ObjectMapper.parserListObject;
import static com.udemyCourse.course.mapper.ObjectMapper.parserObject;

import com.udemyCourse.course.mapper.custom.PersonMapper;
import com.udemyCourse.course.model.Person;
import com.udemyCourse.course.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {


    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper converter;


    public List<PersonDTO> findAll(){
        logger.info("Finding All PersonDTO!");
        var persons = parserListObject(repository.findAll(),PersonDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }
    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        var entity= repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        var dto =  parserObject(entity,PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }




    private PersonDTO mockPersonDTO(int i) {
        logger.info("Finding one PersonDTO!"+i);
        PersonDTO PersonDTO = new PersonDTO();
        PersonDTO.setId(counter.incrementAndGet());
        PersonDTO.setFirstname(""+i);
        PersonDTO.setLastname(""+i);
        PersonDTO.setAddres(" "+i);
        PersonDTO.setGender(""+i);
        return PersonDTO;
    }


    public PersonDTO create( PersonDTO person){
        logger.info("Create one PersonDTO!");
        if(person == null) throw new RequireObjectsIsNullException();
        var entity = parserObject(person,Person.class);
        var dto = parserObject(repository.save(entity),PersonDTO.class);
        addHateoasLinks(dto);
        return  dto;
    }

    public PersonDTO update( PersonDTO person){
        logger.info("Update one PersonDTO!");
        Person entity =  repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        entity.setFirstname(person.getFirstname());
        entity.setLastname(person.getLastname());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

      var dto = parserObject(repository.save(entity),PersonDTO.class);
      addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one PersonDTO!");
        Person entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        repository.delete(entity);

    }

    public PersonDTOv2 createv2( PersonDTOv2 person){
        logger.info("Create one PersonDTOV2!");
        var entity = parserObject(person,Person.class);
        var dto = converter.convertEntityToDTO(repository.save(entity));
        return  dto;
    }


    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));


    }
}
