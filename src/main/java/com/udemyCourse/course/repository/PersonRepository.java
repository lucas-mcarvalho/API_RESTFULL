package com.udemyCourse.course.repository;

import com.udemyCourse.course.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
