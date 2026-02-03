package com.udemyCourse.course.dataDTO.Security;

import java.util.Date;
import java.util.Objects;

public class TokenDTO {

    private String username;
    private String accesToken;
    private String refreshToken;
    private Boolean authenticated;
    private Date created;
    private Date expiration;


    public TokenDTO(String username, String accesToken, String refreshToken, Boolean authenticated, Date created, Date expiration) {
        this.username = username;
        this.accesToken = accesToken;
        this.refreshToken = refreshToken;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TokenDTO tokenDTO)) return false;
        return Objects.equals(username, tokenDTO.username) && Objects.equals(accesToken, tokenDTO.accesToken) && Objects.equals(refreshToken, tokenDTO.refreshToken) && Objects.equals(authenticated, tokenDTO.authenticated) && Objects.equals(created, tokenDTO.created) && Objects.equals(expiration, tokenDTO.expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, accesToken, refreshToken, authenticated, created, expiration);
    }
}
