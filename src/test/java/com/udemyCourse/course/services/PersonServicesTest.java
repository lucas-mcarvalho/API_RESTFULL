package com.udemyCourse.course.services;

import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.exceptions.RequireObjectsIsNullException;
import com.udemyCourse.course.model.Person;
import com.udemyCourse.course.repository.PersonRepository;
import com.udemyCourse.course.unitests.mapper.mocks.MockPerson;
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
class PersonServicesTest {

    MockPerson input;

    @Mock
    PersonRepository repository;

    @InjectMocks
    private PersonServices services;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/1") && link.getType().equals("GET")
                ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

    assertEquals("Address Test1",result.getAddres());
    assertEquals("First Name Test1",result.getFirstname());
    assertEquals("Last Name Test1",result.getLastname());
    assertEquals("Female",result.getGender());

    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted =person;
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);


        when(repository.save(person)).thenReturn(persisted);
        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1",result.getAddres());
        assertEquals("First Name Test1",result.getFirstname());
        assertEquals("Last Name Test1",result.getLastname());
        assertEquals("Female",result.getGender());

    }

    @Test
    void TestCreateWhithNullPerson(){

        Exception exception = assertThrows(RequireObjectsIsNullException.class,()->
                {services.create(null);});
     String expectedMessage = "It is not allowed to persist a null objetct";
     String actualMessage = exception.getMessage();
     assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        Person persisted =person;
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);
        var result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));


        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1",result.getAddres());
        assertEquals("First Name Test1",result.getFirstname());
        assertEquals("Last Name Test1",result.getLastname());
        assertEquals("Female",result.getGender());



    }

    @Test
    void delete() {

        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        services.delete(1L);
        verify(repository,times(1)).findById(anyLong());
        verify(repository,times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {

        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = services.findAll();

        assertNotNull(people);
        assertNotNull(people);
        assertEquals(14,people.size());

        var personone = people.get(1);


        assertNotNull(personone);
        assertNotNull(personone.getId());
        assertNotNull(personone.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));


        assertNotNull(personone.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personone.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personone.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personone.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1",personone.getAddres());
        assertEquals("First Name Test1",personone.getFirstname());
        assertEquals("Last Name Test1",personone.getLastname());
        assertEquals("Female",personone.getGender());


        var personfor = people.get(4);


        assertNotNull(personfor);
        assertNotNull(personfor.getId());
        assertNotNull(personfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/4") && link.getType().equals("GET")
        ));


        assertNotNull(personfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personfor.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/4") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test4",personfor.getAddres());
        assertEquals("First Name Test4",personfor.getFirstname());
        assertEquals("Last Name Test4",personfor.getLastname());
        assertEquals("Male",personfor.getGender());

        var personseven = people.get(7);


        assertNotNull(personseven);
        assertNotNull(personseven.getId());
        assertNotNull(personseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person/7") && link.getType().equals("GET")
        ));


        assertNotNull(personseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&&
                link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&&
                link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("UPDATE")&&
                link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personseven.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&&
                link.getHref().endsWith("/delete/7") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test7",personseven.getAddres());
        assertEquals("First Name Test7",personseven.getFirstname());
        assertEquals("Last Name Test7",personseven.getLastname());
        assertEquals("Female",personseven.getGender());





    }
}