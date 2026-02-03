package com.udemyCourse.course.repository;

import com.udemyCourse.course.model.Book;
import com.udemyCourse.course.model.Person;
import com.udemyCourse.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findByUsername(@Param("username")String username);


}