package com.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

enum Role {
    NORMAL_USER, ADMINISTRATOR
}

public class User implements UserDetails {

    private String id;
    private String username;
    private String password;
    private boolean isOriginalPassword;
    private Role role;
    private String dateOfBirth;
    private String eMail;

    public User() {

    }

    public User(String username, String password, Role role, String dateOfBirth, String eMail) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isOriginalPassword = false;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new LinkedList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(Role.NORMAL_USER.toString()));
        if (role.equals(Role.ADMINISTRATOR)) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMINISTRATOR.toString()));
        }
        return simpleGrantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isOriginalPassword() {
        return isOriginalPassword;
    }

    public void setOriginalPassword(boolean originalPassword) {
        isOriginalPassword = originalPassword;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }


}
