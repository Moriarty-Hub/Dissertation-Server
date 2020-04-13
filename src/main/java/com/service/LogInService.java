package com.service;

import com.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class LogInService {

    private final UserMapper userMapper;

    public LogInService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


}
