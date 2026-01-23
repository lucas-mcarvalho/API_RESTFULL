package com.udemyCourse.course.controllers;

import com.udemyCourse.course.controllers.docs.BookControllerDocs;
import com.udemyCourse.course.dataDTO.v1.BookDTO;
import com.udemyCourse.course.unittests.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController implements BookControllerDocs {

    @Autowired
     private BookServices service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    @Override
    public BookDTO findById(@PathVariable("id") Long id){
        var book = service.findById(id);
        return book;
    }


    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    @Override
    public List<BookDTO> findAll(){
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

    @Override
    public BookDTO create(@RequestBody BookDTO bookDTO){
            return service.create(bookDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})

    @Override
    public BookDTO update(@RequestBody BookDTO bookDTO){
        return service.update(bookDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/v2", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    @Override
    public BookDTO createV2(@RequestBody BookDTO bookDTO){
        return service.create(bookDTO);
    }

}
