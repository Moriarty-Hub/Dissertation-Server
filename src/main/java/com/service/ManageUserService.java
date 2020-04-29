package com.service;

import com.entity.Role;
import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ManageUserService {

    private final MailService mailService;
    private final UserMapper userMapper;

    public ManageUserService(MailService mailService, UserMapper userMapper) {
        this.mailService = mailService;
        this.userMapper = userMapper;
    }

    public void insertNewUserAndSendAccountInfo(String username, String email, String dateOfBirth, String department, String jobTitle) {
        String id = UUID.randomUUID().toString();
        Random random = new Random();
        String password = String.format("%06d", random.nextInt(1000000));
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String isActive = String.valueOf(0);
        String avatar = "default.jpg";
        userMapper.insertUser(id, username, encodedPassword, isActive, Role.NORMAL_USER.toString(), dateOfBirth, email, avatar, department, jobTitle);
        String subject = "Your account for dissertation server";
        String content = "Username: " + username + "\nPassword: " + password;
        mailService.sendMail(email, subject, content);
    }

    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }
}
