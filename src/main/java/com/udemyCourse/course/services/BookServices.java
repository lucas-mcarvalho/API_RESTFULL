package com.udemyCourse.course.services;

import com.udemyCourse.course.controllers.BookController;
import com.udemyCourse.course.dataDTO.v1.BookDTO;
import com.udemyCourse.course.exceptions.RequireObjectsIsNullException;
import com.udemyCourse.course.exceptions.ResourceNotFoundException;
import static com.udemyCourse.course.mapper.ObjectMapper.parserListObject;
import static com.udemyCourse.course.mapper.ObjectMapper.parserObject;

import com.udemyCourse.course.model.Book;
import com.udemyCourse.course.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookServices {


    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());
    @Autowired
    private BookRepository repository;



    public List<BookDTO> findAll(){
        logger.info("Finding All BookDTO!");
        var Books = parserListObject(repository.findAll(),BookDTO.class);
        Books.forEach(this::addHateoasLinks);
        return Books;
    }
    public BookDTO findById(Long id){
        logger.info("Finding one Book!");
        var entity= repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        var dto =  parserObject(entity,BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }




    private BookDTO mockBookDTO(int i) {
        logger.info("Finding one BookDTO!"+i);
        BookDTO BookDTO = new BookDTO();
        BookDTO.setId(counter.incrementAndGet());
        BookDTO.setAuthor(""+i);
        //BookDTO.setLaunch_date(""+i);
        BookDTO.setTitle(" "+i);
       // BookDTO.setPrice(""+i);
        return BookDTO;
    }


    public BookDTO create( BookDTO Book){
        logger.info("Create one BookDTO!");
        if(Book == null) throw new RequireObjectsIsNullException();
        var entity = parserObject(Book,Book.class);
        var dto = parserObject(repository.save(entity),BookDTO.class);
        addHateoasLinks(dto);
        return  dto;
    }

    public BookDTO update( BookDTO Book){
        logger.info("Update one BookDTO!");
        Book entity =  repository.findById(Book.getId()).orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        entity.setAuthor(Book.getAuthor());
        entity.setLaunch_date(Book.getLaunch_date());
        entity.settitle(Book.getTitle());
        entity.setPrice(Book.getPrice());

      var dto = parserObject(repository.save(entity),BookDTO.class);
      addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one BookDTO!");
        Book entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record for this id!"));
        repository.delete(entity);

    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));


    }
}
