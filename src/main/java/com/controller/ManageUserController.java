package com.controller;

import com.entity.User;
import com.mapper.DepartmentMapper;
import com.mapper.JobTitleMapper;
import com.service.ManageUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageUserController {

    private final ManageUserService manageUserService;
    private final DepartmentMapper departmentMapper;
    private final JobTitleMapper jobTitleMapper;

    private static final String AVATAR_FOLDER_PATH = "/avatar/";

    public ManageUserController(ManageUserService manageUserService, DepartmentMapper departmentMapper, JobTitleMapper jobTitleMapper) {
        this.manageUserService = manageUserService;
        this.departmentMapper = departmentMapper;
        this.jobTitleMapper = jobTitleMapper;
    }

    @GetMapping("/manage-user")
    public String showUserListPage(Model model) {
        model.addAttribute("userList", manageUserService.selectAllUsers());
        return "manage-user";
    }

    @PostMapping("/add-new-user")
    public String addNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String dateOfBirth, @RequestParam String department, @RequestParam String jobTitle, Model model) {
        manageUserService.insertNewUserAndSendAccountInfo(username, email, dateOfBirth, department, jobTitle);
        model.addAttribute("hasInfo", true);
        model.addAttribute("info", "The new user was added successfully.");
        return "redirect:index";
    }

    @GetMapping("/edit-user")
    public String showEditUserPage(@RequestParam String id, Model model) {
        User user = manageUserService.selectUserById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("isActive", user.isActive());
        model.addAttribute("role", user.getRole().toString());
        model.addAttribute("dateOfBirth", user.getDateOfBirth());
        model.addAttribute("eMail", user.getEMail());
        model.addAttribute("avatar", AVATAR_FOLDER_PATH + user.getAvatar());
        model.addAttribute("department", user.getDepartment());
        model.addAttribute("jobTitle", user.getJobTitle());
        model.addAttribute("departmentList", departmentMapper.selectAllDepartmentName());
        model.addAttribute("jobTitleList", jobTitleMapper.selectAllJobTitle());
        return "edit-user";
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestParam String id, @RequestParam String username, @RequestParam String dateOfBirth,
                           @RequestParam String eMail, @RequestParam String department, @RequestParam String jobTitle,
                             Model model) {
        if (!manageUserService.isUsernameHasChanged(id, username)) {
            manageUserService.updateUserProfile(id, username, dateOfBirth, eMail, department, jobTitle);
            model.addAttribute("isUsernameValid", true);
        } else if (manageUserService.isUsernameHasChanged(id, username) && manageUserService.isUsernameExist(username)) {
            manageUserService.updateUserProfile(id, username, dateOfBirth, eMail, department, jobTitle);
            model.addAttribute("isUsernameValid", true);
        } else {
            model.addAttribute("isUsernameValid", false);
        }
        return "redirect:edit-user?id=" + id;
    }

    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam String id) {
        manageUserService.deleteUser(id);
        return "redirect:/manage-user";
    }
}
