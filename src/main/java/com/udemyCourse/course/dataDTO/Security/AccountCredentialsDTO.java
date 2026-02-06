package com.udemyCourse.course.dataDTO.Security;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;



public class AccountCredentialsDTO extends RepresentationModel<AccountCredentialsDTO> implements Serializable {

    private static final long SerialVersionUID = 1L;


    private String username;
    private String fullname;
    private String password;

    public AccountCredentialsDTO() {
    }

    public AccountCredentialsDTO(String username, String fullname, String password) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }

    public AccountCredentialsDTO(Link initialLink, String username, String fullname, String password) {
        super(initialLink);
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }

    public AccountCredentialsDTO(Iterable<Link> initialLinks, String username, String fullname, String password) {
        super(initialLinks);
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AccountCredentialsDTO that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(username, that.username) && Objects.equals(fullname, that.fullname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, fullname, password);
    }
}
