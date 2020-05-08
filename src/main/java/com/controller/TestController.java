package com.controller;

import com.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final UserMapper userMapper;

    public TestController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/test")
    public void test() {
        if (userMapper.selectUserByUsername("xdong1") == null) {
            System.out.println("Null");
        } else {
            System.out.println("Not null");
        }
    }
}
