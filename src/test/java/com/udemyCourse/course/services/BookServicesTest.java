package com.udemyCourse.course.services;

import com.udemyCourse.course.dataDTO.v1.BookDTO;
import com.udemyCourse.course.exceptions.RequireObjectsIsNullException;
import com.udemyCourse.course.model.Book;
import com.udemyCourse.course.repository.BookRepository;
import com.udemyCourse.course.unittests.mapper.mocks.mapper.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @Mock
    BookRepository repository;

    @InjectMocks
    private BookServices services;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book Book = input.mockEntity(1);
        Book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(Book));
        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/1") && link.getType().equals("GET")
                ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

    assertEquals("Some Author1",result.getAuthor());
    assertEquals(25D,result.getPrice());
    assertEquals("Some Title1",result.getTitle());
    assertNotNull(result.getLaunch_date());

    }

    @Test
    void create() {
        Book Book = input.mockEntity(1);
        Book persisted =Book;
        Book.setId(1L);

        BookDTO dto = input.mockDTO(1);


        when(repository.save(any(Book.class))).thenReturn(persisted);
        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/1") && link.getType().equals("GET")
        ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Some Author1",result.getAuthor());
        assertEquals(25D,result.getPrice());
        assertEquals("Some Title1",result.getTitle());
        assertNotNull(result.getLaunch_date());
    }

    @Test
    void TestCreateWhithNullBook(){

        Exception exception = assertThrows(RequireObjectsIsNullException.class,()->
                {services.create(null);});
     String expectedMessage = "It is not allowed to persist a null objetct";
     String actualMessage = exception.getMessage();
     assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Book Book = input.mockEntity(1);
        Book persisted =Book;
        Book.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(Book));
        when(repository.save(Book)).thenReturn(persisted);
        var result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/1") && link.getType().equals("GET")
        ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Some Author1",result.getAuthor());
        assertEquals(25D,result.getPrice());
        assertEquals("Some Title1",result.getTitle());
        assertNotNull(result.getLaunch_date());

    }

    @Test
    void delete() {

        Book Book = input.mockEntity(1);
        Book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(Book));
        services.delete(1L);
        verify(repository,times(1)).findById(anyLong());
        verify(repository,times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {

        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> Book = services.findAll();

        assertNotNull(Book);
        assertNotNull(Book);
        assertEquals(14,Book.size());

        var Bookone = Book.get(1);


        assertNotNull(Bookone);
        assertNotNull(Bookone.getId());
        assertNotNull(Bookone.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/1") && link.getType().equals("GET")
        ));


        assertNotNull(Bookone.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(Bookone.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(Bookone.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(Bookone.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Some Author1",Bookone.getAuthor());
        assertEquals(25D,Bookone.getPrice());
        assertEquals("Some Title1",Bookone.getTitle());
        assertNotNull(Bookone.getLaunch_date());

        var Bookfor = Book.get(4);


        assertNotNull(Bookfor);
        assertNotNull(Bookfor.getId());
        assertNotNull(Bookfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/4") && link.getType().equals("GET")
        ));


        assertNotNull(Bookfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(Bookfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(Bookfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(Bookfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/4") && link.getType().equals("DELETE")
        ));

        assertEquals("Some Author4",Bookfor.getAuthor());
        assertEquals(25D,Bookfor.getPrice());
        assertEquals("Some Title4",Bookfor.getTitle());
        assertNotNull(Bookfor.getLaunch_date());
        var Bookseven = Book.get(7);


        assertNotNull(Bookseven);
        assertNotNull(Bookseven.getId());
        assertNotNull(Bookseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book/7") && link.getType().equals("GET")
        ));


        assertNotNull(Bookseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/Book") && link.getType().equals("GET")
        ));

        assertNotNull(Bookseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/Book") && link.getType().equals("POST")
        ));

        assertNotNull(Bookseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/Book") && link.getType().equals("PUT")
        ));

        assertNotNull(Bookseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/7") && link.getType().equals("DELETE")
        ));

        assertEquals("Some Author7",Bookseven.getAuthor());
        assertEquals(25D,Bookseven.getPrice());
        assertEquals("Some Title7",Bookseven.getTitle());
        assertNotNull(Bookseven.getLaunch_date());




    }
}