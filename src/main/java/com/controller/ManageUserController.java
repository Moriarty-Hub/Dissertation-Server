package com.controller;

import com.service.ManageUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageUserController {

    private final ManageUserService manageUserService;

    public ManageUserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @GetMapping("/add-new-user")
    public String showAddNewUserPage() {
        return "add-new-user";
    }

    @PostMapping("/add-new-user")
    public String addNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String dateOfBirth, Model model) {
        manageUserService.insertNewUserAndSendAccountInfo(username, email, dateOfBirth);
        model.addAttribute("hasInfo", true);
        model.addAttribute("info", "The new user was added successfully.");
        return "redirect:index";
    }

    @GetMapping("/user-list-page")
    public String showUserListPage(Model model) {
        model.addAttribute("userList", manageUserService.selectAllUsers());
        return "user-list-page";
    }


}
