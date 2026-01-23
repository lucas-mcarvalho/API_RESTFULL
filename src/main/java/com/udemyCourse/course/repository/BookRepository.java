package com.udemyCourse.course.repository;

import com.udemyCourse.course.model.Book;
import com.udemyCourse.course.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
