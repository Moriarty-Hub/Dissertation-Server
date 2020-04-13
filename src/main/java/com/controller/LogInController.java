package com.controller;

import com.service.LogInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {

    private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping("/login")
    public String showLogInPage() {
        return "login";
    }


}
