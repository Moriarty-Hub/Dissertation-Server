package com.controller;

import com.entity.User;
import com.service.ProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProfileController {

    private static final String AVATAR_FOLDER_PATH = "/avatar/";

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("avatar", AVATAR_FOLDER_PATH + user.getAvatar());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("dateOfBirth", user.getDateOfBirth());
        model.addAttribute("email", user.getEMail());
        model.addAttribute("department", user.getDepartment());
        model.addAttribute("jobTitle", user.getJobTitle());
        return "profile";
    }

    @GetMapping("/modify-avatar")
    public String showModifyAvatarPage() {
        return "modify-avatar";
    }

    @PostMapping("/modify-avatar")
    public String modifyAvatar(@RequestParam MultipartFile avatar, Model model) {
        String currentUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        try {
            profileService.updateAvatar(currentUserId, avatar);
        } catch (IOException e) {
            model.addAttribute("isError", true);
            model.addAttribute("info", e.getMessage());
            return "/modify-avatar";
        }
        return "redirect:profile";
    }
}
