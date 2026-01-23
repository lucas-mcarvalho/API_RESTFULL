package com.udemyCourse.course.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 180)
    private String author;
    @Column(name = "launch_date" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launch_date;
    @Column(nullable = false)
    private  Double price;
    @Column(name = "title", nullable = false, length = 250)
    private String title;
    public Book(){

    }

    public Book(Long id, String author, Date launch_date, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book books)) return false;
        return id == books.id && Objects.equals(author, books.author) && Objects.equals(launch_date, books.launch_date) && Objects.equals(price, books.price) && Objects.equals(title, books.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launch_date, price, title);
    }
}
