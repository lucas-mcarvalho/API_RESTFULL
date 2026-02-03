package com.udemyCourse.course.model;

import jakarta.persistence.*;
import org.hibernate.boot.internal.GenerationStrategyInterpreter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "full_name")
    private String fullname;


    @Column
    private String password;

    @Column(name = "account_non_expired")
    private Boolean account_non_expired;

    @Column(name = "account_non_locked")
    private Boolean account_non_locked;

    @Column(name = "credentials_non_expired")
    private Boolean credentials_non_expired;

    @Column
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
    joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for(Permission permission : permissions){
            roles.add(permission.getDescription());
        }
        return roles;
    }

    public User() {
    }

    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.account_non_locked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return credentials_non_expired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccount_non_expired() {
        return account_non_expired;
    }

    public void setAccount_non_expired(Boolean account_non_expired) {
        this.account_non_expired = account_non_expired;
    }

    public Boolean getAccount_non_locked() {
        return account_non_locked;
    }

    public void setAccount_non_locked(Boolean account_non_locked) {
        this.account_non_locked = account_non_locked;
    }

    public Boolean getCredentials_non_expired() {
        return credentials_non_expired;
    }

    public void setCredentials_non_expired(Boolean credentials_non_expired) {
        this.credentials_non_expired = credentials_non_expired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(fullname, user.fullname) && Objects.equals(password, user.password) && Objects.equals(account_non_expired, user.account_non_expired) && Objects.equals(account_non_locked, user.account_non_locked) && Objects.equals(credentials_non_expired, user.credentials_non_expired) && Objects.equals(enabled, user.enabled) && Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullname, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, permissions);
    }


}
