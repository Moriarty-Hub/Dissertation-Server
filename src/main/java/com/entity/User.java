package com.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class User implements UserDetails {

    private String id;
    private String username;
    private String password;
    private boolean isActive;
    private Role role;
    private String dateOfBirth;
    private String eMail;
    private String avatar;
    private String department;
    private String jobTitle;

    public User() {

    }

    public User(String username, String password, Role role, String dateOfBirth, String eMail, String avatar, String department, String jobTitle) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = false;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
        this.avatar = avatar;
        this.department = department;
        this.jobTitle = jobTitle;
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
        if (!isActive()) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("INACTIVE"));
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
